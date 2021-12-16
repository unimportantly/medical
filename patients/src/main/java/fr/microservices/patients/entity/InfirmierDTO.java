package fr.microservices.patients.entity;

import lombok.*;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InfirmierDTO {

    private String idInfirmier;
    private String nom;
    private String prenom;

}
