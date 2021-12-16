package org.example.deplacements.repository;

import org.example.deplacements.dto.DeplacementDetailsDTO;
import org.example.deplacements.entity.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class PatientRepository {

    /*
    declaring the webclient singleton as an attribute then inject it inside the repository
    through the constructor.
    Since the module has 2 webclients, have to qualify them
     */
    private WebClient webClient;

    public PatientRepository(@Qualifier("webClientPatients") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * queries the patient microservice for details on a single patient
     * @param id String pointing to the patient inside the db
     * @return a Mono object of the patient entity
     */
    public Mono<Patient> getPatientDetails(String id){
        return webClient.get()
                .uri("/patients/" + id)
                .retrieve()
                .bodyToMono(Patient.class);
    }
}
