  [![Fulstack2](http://gitlab.altair.recouv/sded/bureau-technique/architecture-expertise-applicative/zone-ressources/images-bt/raw/master/fullstack.png)](https://recouv.sharepoint.com/sites/AcossDSI-SDED-BT-Collaborationtechnique/SitePages/Fullstack.aspx)
                                                         
  [![Normes et docs](http://gitlab.altair.recouv/sded/bureau-technique/architecture-expertise-applicative/zone-ressources/images-bt/raw/master/sharepoint-35.png)](https://recouv.sharepoint.com/sites/AcossDSI-SDED-BT-Collaborationtechnique/Lists/RefDocApplicables/Carte%20Logo%20Gauche.aspx)
  [![Sources Fullstack](http://gitlab.altair.recouv/sded/bureau-technique/architecture-expertise-applicative/zone-ressources/images-bt/raw/master/gitlab-35.png)](http://gitlab.altair.recouv/sded/bureau-technique/architecture-expertise-applicative/fullstack/)
  [![Redmine Fullstack](http://gitlab.altair.recouv/sded/bureau-technique/architecture-expertise-applicative/zone-ressources/images-bt/raw/master/redmine-35.png)](https://redmine.altair.recouv/projects/archapp/issues)
  [![Contact](http://gitlab.altair.recouv/sded/bureau-technique/architecture-expertise-applicative/zone-ressources/images-bt/raw/master/email-35.png)](mailto:mouadh.lamine@acoss.fr)

[[_TOC_]]

# Livelcti

Cette Application a été générée par l'utilitaire de génération de l'équipe Fullstack. Il est constitué de plusieurs Templates que l'équipe met à disposition pour les MOE. Vous devez consulter la partie "A FAIRE" pour chaque Template pour pouvoir terminer la configuration de votre application.

## Template Default:

Cette Template correspond à un modèle contenant entre autres :

### Des dépendances Maven :
* fullstack-accueil-starter
* fullstack-observability-starter
* fullstack-commons-starter
* fullstack-security-starter
* mapstruct


### A FAIRE:
* le serveur context path est "/livelcti_be/v1", si besoin, vous pouvez le surcharger dans le fichier application-***.yaml:
    * server.servlet.context-path: "/livelcti_be"
* fichier 'application-***.yaml': Modifier les variables
    * fullstack.commons.private-key.location
    * fullstack.commons.public-key.location
    * security.prisme.client.id
    * security.prisme.client.secret

* fichier './src/main/resources/archimed/regles.yaml.exemple': ce fichier d'Archimed n'est qu'un exemple, il faudra le completer et le renommer en "regles.yaml" pour qu'il soit utilisable. Voir [Archimed: Guide du référentiel autorisations](https://readthedocs.cnp.recouv/docs/documentation-archimed/fr/latest/Developpeurs/GuidesSecurite/GuideDuReferentielAutorisations/GuideDuReferentielAutorisations.html)
* fichier './Archimedfile': avec d'autres modifications, il faut renseigner l'url dans la variable "install.assemblage.tokens.url"
* fichier './Jenkinsfile':
    *  Archimed: Si vous avez des paramètres supplémentaires, les ajouter à la fin de la commande '--install  ${params}'. Voir [Archime: Installeur DevOps INTRA](https://readthedocs.cnp.recouv/docs/documentation-archimed/fr/latest/Developpeurs/GuidesTechnique/installeurDevopsIntra.html)

## Template OpenApi:

Cette Template correspond à un modèle contenant entre autres :

### Des contrats openapi :
* api-hello.yaml

### Des plugins Maven :
* openapi-generator-maven-plugin


## Template Database:

Ceci est un template optionnel qui contient la liste des composants suivants :

### Des dépendances Maven :
* fullstack-persistence-starter
* postgresql
* flyway-core

### Des fichiers de connexion à la base de données :
* bdd-h2.yaml
* bdd-pfs.yaml

### Un contrats openapi exposant des webservices CRUD pour tester l'accès et les réactions avec la base de données :
* api-exemple-crud.yaml

### Des classes java pour implémenter cet exemple de CRUD :
* entities
* repositories
* mappers
* services
* controlleur

### A faire :
Dans le template database, c'est Flyway qui est par défaut responsable de la génération et de la migration du schéma de la base de données sur la plateforme PFS.
Il vous reste à ajouter les scripts de migrations dans le chemin src/main/resources/db/migration.

Si vous voulez désintégrer Flyway vous devez supprimer la dépendance flyway-core de votre pom.xml.

Pour remplacer Flyway sur la plateforme PFS vous avez la possibilité d'activer la stratégie de génration du schéma de la base de données par Hibernate :

```
spring:
  jpa:
    generate-ddl: true
    hibernate:
      ddl-auto: create
```
## Template Kafka:

Cette Template correspond à un modèle contenant entre autres :

### Des dépendances Maven :
* fullstack-kafka-starter
* fullstack-kafka-starter-test

### Des fichier de configuration Kafka:
* kafka-assemblage-oauth.yaml
* kafka-assemblage.yaml
* kafka-embedded.yaml
* projet.yaml
### Consumer:
BasicConsumerEx

### Producer:
* BasicProducerExTest
