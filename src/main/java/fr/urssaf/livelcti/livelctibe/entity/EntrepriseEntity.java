package fr.urssaf.livelcti.livelctibe.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
//import javax.persistence.Version;

//import fr.urssaf.fullstack.persistence.core.domain.PersistanceParent;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@ToString
@Entity
@Table(name = "entreprise")
public class EntrepriseEntity /*extends PersistanceParent*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nom_entreprise")
    private String nomEntreprise;
    @Column(name = "siren_siret")
    private String sirenSiret;
    @Column(name = "adresse_siege")
    private String adresseSiege;
    @Column(name = "date_dirigeant")
    private LocalDate dateDirigeant;
    @Column(name = "nom_dirigeant")
    private String nomDirigeant;
    @Column(name = "date_naissance_dirigeant")
    private LocalDate dateNaissanceDirigeant;
    @Column(name = "commentaire_societe")
    private String commentaireSociete;
    @Column(name = "date_debut")
    private LocalDate dateDebut;
    @Column(name = "archive")
    private boolean archive;
    @Column(name = "individus")
    private String individus;
    @Column(name = "inspecteur")
    private String inspecteur;
    // Ajout du champ dt_maj avec les annotations nécessaires
    @Column(name = "dt_maj")
    private LocalDateTime dtmaj;
}
