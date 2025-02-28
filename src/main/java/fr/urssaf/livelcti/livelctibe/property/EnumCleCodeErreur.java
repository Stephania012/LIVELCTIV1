package fr.urssaf.livelcti.livelctibe.property;

import fr.urssaf.fullstack.commons.core.property.CleCodeErreur;

/**
 * Enumération contenant les codes d'erreur liés aux fichiers de propriétés. /src/main/resourses/erreurs.properties
 *
 * Le fichier suit un formattage précis :
 *
 * Le code de l'erreur : erreur.technique.generique.code=...<br>
 * Un message : erreur.technique.generique.message=...<br>
 * Et une description : erreur.technique.generique.description=...<br>
 *
 * Dans l'enum on ne met pas la partie après le dernier point. sur l'exemple uniquement "erreur.technique.generique"
 *
 */
public enum EnumCleCodeErreur implements CleCodeErreur {


    ERREUR_ENTITE_INEXISTANTE("erreur.entite.inexistante"),
    ERREUR_ENTREPRISE_INEXISTANTE("erreur.entreprise.inexistante"),
    ERREUR_ENTREPRISE_ACTIVER_SUIVI("erreur.entreprise.activer.suivi"),
    ERREUR_ADRESSE_INEXISTANTE("erreur.adresse.inexistante"),
    ERREUR_ATTESTATION_INEXISTANTE("erreur.attestation.inexistante"),
    ERREUR_SUIVI_CREATION_ENTREPRISE_ERREUR_INEXISTANTE("erreur.suivi.creation.entreprise.erreur.inexistante"),
    ERREUR_SUIVI_CREATION_ENTREPRISE_INEXISTANTE("erreur.suivi.creation.entreprise.inexistante"),
    ERREUR_COMMUNE_INEXISTANTE("erreur.commune.inexistante"),
    ERREUR_API_CLIENT_FONCTIONNELLE("erreur.fonctionnelle.client.api"),
    ERREUR_API_CLIENT_TECHNIQUE("erreur.technique.client.api");



    /**
     * Code lié au fichier de propriété.
     */
    private final String code;

    /**
     * Constructeur.
     *
     * @param code Code lié au fichier de propriété.
     */
    EnumCleCodeErreur(final String code) {
        this.code = code;
    }

    /**
     * Récupère le code ie le code du fichier de propriété.
     *
     * @return code
     */
    @Override
    public String getCode() {
        return code;
    }

}
