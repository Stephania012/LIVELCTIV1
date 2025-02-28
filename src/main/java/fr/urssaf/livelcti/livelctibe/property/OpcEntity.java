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
@Getter @Setter
@ToString
public class OpcEntity {
private  String opcNumIntervention;
private  String opcRealisateur;
private  String opcDatCreation;
private  String opcAction;
private  String opcPilote;
private  String opcDelegue;
private  String opcEtat;
private  String opcDatEtat;
}
