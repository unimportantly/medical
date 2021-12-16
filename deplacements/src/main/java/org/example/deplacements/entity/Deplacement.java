package org.example.deplacements.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deplacement {

    @Id
    private String id;
    private String idInfirmier;
    private String idPatient;
    private Date date;
    private double cout;
}
