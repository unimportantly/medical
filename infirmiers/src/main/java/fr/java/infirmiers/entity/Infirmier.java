package fr.java.infirmiers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infirmier {

    @Id
    private String idInfirmier;
    private Adresse adresse;
    private Long numero_pro;
    private String nom;
    private String prenom;
    private int tel_pro;
    private int tel_perso;
}
