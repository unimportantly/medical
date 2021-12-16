package fr.java.infirmiers.repository;

import fr.java.infirmiers.entity.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public class PatientRepository {

    private WebClient webClient;

    public PatientRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Patient> findAll(String id){
        Flux<Patient> patients = webClient.get()
                .uri("/patients/infirmiers/" + id)
                .retrieve()
                .bodyToFlux(Patient.class);
        return patients;
    }


}
