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
@Setter
@Getter
@ToString
public class DsnEntity {
private String nir;
private String dsnAdr;
private String dsnActiv;
private String dsnContr;
private String dsnDatEmb;
private String dsnDuree;
private String dsnSalBrut;
private String dsnDrMois;
private String dsnTot;
private String dsnNom;
private String dsnPrenom;
private String dsnDatNaiss;
private String dsnDebContr;
}
