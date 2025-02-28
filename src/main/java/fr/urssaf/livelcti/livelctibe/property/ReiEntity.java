package fr.urssaf.livelcti.livelctibe.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@ToString
public class ReiEntity {
    private String id;
    private String retourSysteme;
    private String reiSiret;
    private String reiLibVoie;
    private String reiNoVoie;
    private String reiRepVoie;
    private String reiTypVoie;
    private String reiBaseUrssaf;
    private String reiCp;
    private String reiCommune;
    private String reiDtFin;
    private String reiSiren;
    private String reiDenomination;
    private String reiDtCreation;
    private String reiDtDebut;
    private String reiQualiteDir;
    private String reiNom;
    private String reiPrenom;
    private String reiDtNaiss;
    private String reiLieuNaiss;
    private String reiTitre;
    private String reiNumCext;
    private String reiNumCint;
}
