package fr.java.infirmiers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfirmierPatient {

    private Infirmier infirmier;
    private List<Patient> patientList;
}
