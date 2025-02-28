package fr.urssaf.livelcti.livelctibe.property;

import fr.urssaf.fullstack.commons.core.property.CleLibelle;

/**
 * Enumération contenant les clés liées à la gestion globale des libelles/labels. Lié au fichier :
 * /src/main/ressourses/labels.properties
 *
 */
public enum EnumCleLibelle implements CleLibelle {


    EXEMPLE("mon.libelle"),
    CLIENT_API_ERREUR_DESCRIPTION("client-api-erreur-description");

    // Libelles metier

    /**
     * Code lié au fichier de propriété.
     */
    private final String code;

    /**
     * Constructeur.
     *
     * @param code Code lié au fichier de propriété.
     */
    EnumCleLibelle(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
