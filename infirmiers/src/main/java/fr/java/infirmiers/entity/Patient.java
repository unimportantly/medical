package fr.java.infirmiers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private String nom;
    private String prenom;
    private String sexe;
    private Long securite_sociale;
}
