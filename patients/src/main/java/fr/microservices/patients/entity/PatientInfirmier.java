package fr.microservices.patients.entity;

import fr.java.infirmiers.entity.Infirmier;
import lombok.*;
import reactor.core.publisher.Mono;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
/*
here we have to create a common object to merge it all together
 */
public class PatientInfirmier {

    private InfirmierDTO infirmier;
    private Patient patient;

    public PatientInfirmier(Mono<InfirmierDTO> infirmier, Patient patient) {
    }
}
