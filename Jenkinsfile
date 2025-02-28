#!groovy

library identifier: 'buildlibrary@master', retriever: modernSCM(
    [$class       : 'GitSCMSource',
     remote       : 'http://gitlab.altair.recouv/sdat/act/saul/fullstack/integration-continue/buildlibrary.git',
     credentialsId: 'GITLAB_ID'])
jobBuild.init()
/**
 * Pipeline permettant de :
 *      Faire un build + Analyse Sonar
 *      Déployer un snapshot sous nexus
 *      Verifier le changelog pour les Merge Requests
 *      Faire une release (déploiement sous nexus + tag git + release GitLab) possible uniquement avec la branche Master
 */
properties(
    [
        parameters([
                choice(
                name: 'BUILD_TYPE',
                choices: [
                        'Build Simple',
                        'Deploy Snapshot',
                        'Deploy Release',
                        'Deploy to Archimed'],

                description: 'Type de construction'
        ),
        string(name: 'version', defaultValue: '2.2.0', description: 'Release Version')
        ]),
        gitLabConnection('gitlab')
    ]
)

node('docker')  {
    MVN_JDK_VERSION = 'MVN_JDK_VERSION=3.9.1-eclipse-temurin-17'
    HOME = '.'
    VERSION = ''
    MVN_ADDITIONAL = " -Dprojet.kafka.prefixe-couloir=dev01 -Duser.timezone=Europe/Paris" 

    try {
            //Checkout scm
            scriptedCheckoutScm()

            // Traitement des parameters et job automatique
            BUILD_TYPE = ''
            if (gitTools.isMrAccepted()) {
                BUILD_TYPE = 'Merge Request Accepted'
            }else if (gitTools.isMrOpened()) {
                BUILD_TYPE = 'Merge Request Opened'
            }else {
                BUILD_TYPE = params.BUILD_TYPE
            }

             if (BUILD_TYPE != 'Deploy to Archimed') { 
                buildImage = docker.build('mvnjdk17', "--build-arg ${MVN_JDK_VERSION} .")
            }

            switch (BUILD_TYPE) {
                case 'Deploy Snapshot':
                    buildImage.inside() {
                        setVersion()
                        mavenBuildEtSonar()
                        qualityGate()
                        deploySnapshot()
                    }
                    break
                case 'Deploy Release':
                    buildImage.inside() {
                        setVersion()
                        mavenBuildEtSonar()
                        qualityGate()
                        mavenRelease()
                    }
                    break
                case 'Merge Request Opened':
                    buildImage.inside() {
                        switchToGitSourceBranch()
                        checkChangelog()
                        setVersion()
                        mavenBuildEtSonar()
                        qualityGate()
                    }
                    break
                case 'Merge Request Accepted':
                    buildImage.inside() {
                        setVersion()
                        mavenBuildEtSonar()
                        qualityGate()
                        deploySnapshot()
                    }
                    break
                case 'Deploy to Archimed':
                    deployArchimed()
                    break
                default:
                    buildImage.inside() {
                        setVersion()
                        mavenBuildEtSonar()
                        qualityGate()
                    }
                    break
            }
            postSuccess()
        } catch (e) {
            postFailure(e)
            error(e.message)
    }
}
/**
 * Méthode pour les traitement dans le cas d'érreur
 * Mettre a jour le statut du currentBuild
 * Mettre a jour le statu du projet dans Gitlab
 * Envoyer des email aux developer et/ou  groupe
 * @param e: Exception
 *
 */
def postFailure(e) {
    stage('Scripted: Post Actions') {
        currentBuild.result = 'FAILURE'
        println "Failed because of $e"
        updateGitlabCommitStatus name: 'build', state: 'failed'
        jobBuild.sendMail('failure', 'mouadh.lamine@acoss.fr')
    }
}
/**
 * Méthode pour les traitement dans le cas de success
 * Mettre a jour le statut du currentBuild
 * Mettre a jour le statu du projet dans Gitlab
 *
 */
def postSuccess() {
    stage('Scripted: Post Actions') {
        currentBuild.result = 'SUCCESS'
        updateGitlabCommitStatus name: 'build', state: 'success'
    }
}
/**
 * Méthode/Stage Scripted: Checkout SCM
 *
 */
def scriptedCheckoutScm() {
    stage('Scripted: Checkout SCM') {
        // Initialise scmVars du libraries gitTools avec les variables du checkout scm
        gitTools.scmVars = checkout scm
        // Initialiser 'env.BRANCH_NAME'
        env.BRANCH_NAME = gitTools.getBrancheName()
    }
}

/**
 * Méthode/Stage de verification de modification de Changelog
 * Utiliser dans les cas de MR OPENED
 *
 */
def checkChangelog() {
    stage('Vérification Changelog') {
        changeLogTools.checkIfCHANGELOGHasChanged()
    }
}
/**
 * Méthode/Stage de Changer vers Source Branch
 * Utiliser dans les cas de MR OPENED
 *
 */
def switchToGitSourceBranch() {
    stage('Changer vers Source Branch') {
        sh 'git config --local credential.helper "!f() { echo username=\\$GIT_AUTH_USR; echo password=\\$GIT_AUTH_PSW; }; f"'
        withCredentials([usernamePassword(credentialsId: 'GITLAB_ID', usernameVariable: 'GIT_AUTH_USR', passwordVariable: 'GIT_AUTH_PSW')]) {
            sh "git checkout ${env.gitlabSourceBranch}"
            env.BRANCH_NAME = "${env.gitlabSourceBranch}".replaceAll('origin/', '')
            sh 'git pull'
        }
    }
}
/**
 * Méthode/Stage de Définition de version
 * Mettre a jour le statut du currentBuild
 * Mettre a jour le statu du projet dans Gitlab
 * Envoyer des email aux developer et/ou  groupe
 *
 */
def setVersion() {
    stage('Définition de version') {
        // On récupère le nom du branche pour pouvoir l'utiliser dans la suite du build
        brancheGit = "${BRANCH_NAME.replaceAll('/', '-').replaceAll('origin-', '')}"
        VERSION = params.version
        CHANGELIST = ''
        if (brancheGit != 'master') {
            if (gitTools.isMrAccepted() || brancheGit == 'develop') {
                VERSION = 'latest'
            }else {
                VERSION = "${VERSION}-${brancheGit}"
            }
            CHANGELIST = '-SNAPSHOT'
        }
        echo "Type de build : ${params.BUILD_TYPE}"
        echo "Version : ${VERSION}${CHANGELIST}"
    }
}
/**
 * Méthode/Stage de Maven verify et Analyse Sonarqube
 *
 */
def mavenBuildEtSonar() {
    stage('Maven verify et Analyse Sonarqube') {
        withSonarQubeEnv('Sonarqube HORS-COBOL') {
            // Build maven en utilisant les settings maven configurés dans jenkins
            configFileProvider([configFile(fileId: 'Global_Maven_Settings', variable: 'MAVEN_SETTINGS_XML')]) {
                withCredentials([usernamePassword(credentialsId: 'GITLAB_ID', usernameVariable: 'GIT_AUTH_USR', passwordVariable: 'GIT_AUTH_PSW')]) {
                    sh """
                        mvn --no-transfer-progress -s $MAVEN_SETTINGS_XML -Drevision=${VERSION} -Dchangelist=${CHANGELIST} verify sonar:sonar -Dsonar.branch.name=${BRANCH_NAME} \
                        -Dlogging.level.root=ERROR -Dspring.main.banner-mode=off ${MVN_ADDITIONAL}
                    """
                }
            }
        }
    }
}
/**
 * Méthode/Stage de Vérification Quality Gate Sonar
 *
 */
def qualityGate() {
    stage('Vérification Quality Gate Sonar') {
        timeout(time: 2, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        }
    }
}
/**
 * Méthode/Stage de Déploiement snapshot
 *
 */
def deploySnapshot() {
    stage('Déploiement snapshot') {
        configFileProvider([configFile(fileId: 'Global_Maven_Settings', variable: 'MAVEN_SETTINGS_XML')]) {
            withSonarQubeEnv('Sonarqube HORS-COBOL') {
                withCredentials([usernamePassword(credentialsId: 'GITLAB_ID', usernameVariable: 'GIT_AUTH_USR', passwordVariable: 'GIT_AUTH_PSW')]) {
                    sh "mvn  --no-transfer-progress -s $MAVEN_SETTINGS_XML -Drevision=${VERSION} -Dchangelist=${CHANGELIST} clean deploy ${MVN_ADDITIONAL}"
                }
            }
        }
    }
}
/**
 * Méthode/Stage de Maven Release
 *
 */
def mavenRelease() {
    stage('Release') {
        configFileProvider([configFile(fileId: 'Global_Maven_Settings', variable: 'MAVEN_SETTINGS_XML')]) {
            withSonarQubeEnv('Sonarqube HORS-COBOL') {
                withCredentials([usernamePassword(credentialsId: 'GITLAB_ID', usernameVariable: 'GIT_AUTH_USR', passwordVariable: 'GIT_AUTH_PSW')]) {
                    sh 'git config --local credential.helper "!f() { echo username=\\$GIT_AUTH_USR; echo password=\\$GIT_AUTH_PSW; }; f"'
                    sh "mvn --no-transfer-progress -s $MAVEN_SETTINGS_XML -Drevision=${VERSION} -Dchangelist=${CHANGELIST} clean deploy ci-friendly-flatten:scmTag ${MVN_ADDITIONAL}"
                    gitTools.doRelease()
                    env.FULLSTACK_BE_TAG_VERSION = sh(returnStdout: true, script: 'git describe --tags').trim()
                }
            }
        }
    }
}

/**
 * Méthode/Stage de Deployment/Installer le bundle Archimed
 *
 */
def deployArchimed() {
    stage('ARCHIMED: install') {
        docker.image('acoss/generique/executor-make-archimed-bundle-snapshot:latest').inside {
            withCredentials([usernamePassword(credentialsId: 'FEDEX_PUBLISH', passwordVariable: 'CPT_TECHNIQUE_PASSWORD', usernameVariable: 'CPT_TECHNIQUE_USERNAME')]) {
                sh "java -cp /archimed.jar fr.acoss.mediation.archimed.main.ArchimedPlugin --username=$CPT_TECHNIQUE_USERNAME --password=$CPT_TECHNIQUE_PASSWORD --install"
            }
        }
    }
}
