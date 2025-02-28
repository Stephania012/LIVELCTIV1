package fr.urssaf.livelcti.livelctibe.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Salaire {
private String mensuelle;
private String heure;
private String journee;

}
