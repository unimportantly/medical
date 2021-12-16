package org.example.deplacements.dto;

import lombok.*;
import org.example.deplacements.entity.Infirmier;
import org.example.deplacements.entity.Patient;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeplacementDetailsDTO {

    private String id;
    private Infirmier infirmier;
    private Patient patient;
    private Date date;
    private double cout;
}
