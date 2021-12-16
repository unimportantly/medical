package org.example.deplacements.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeplacementDTO {

    private String id;
    private String idInfirmier;
    private String idPatient;
    private Date date;
    private double cout;
}
