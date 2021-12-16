package fr.microservices.patients.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Getter
@Setter
public class Patient {

    @Id
    private String idPatient;
    private String nom;
    private String prenom;
    private Date naissance;
    private String sexe;
    private Long securite_sociale;
    private Adresse adresse;
    private String idInfirmier;
}
