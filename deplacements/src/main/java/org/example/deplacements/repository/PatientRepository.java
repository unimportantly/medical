package org.example.deplacements.repository;

import org.example.deplacements.dto.DeplacementDetailsDTO;
import org.example.deplacements.entity.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class PatientRepository {

    private WebClient webClient;

    public PatientRepository(@Qualifier("webClientPatients") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Patient> getPatientDetails(String id){
        return webClient.get()
                .uri("/patients/" + id)
                .retrieve()
                .bodyToMono(Patient.class);
    }
}
