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
@Getter
@Setter
@ToString
public class DpaeEntity {
private String dpaeNom;
private String dpaePrenom;
private String dpaeDatNaiss;
private String dpaeDatEmb;
private String dpaeDatDecl;
}
