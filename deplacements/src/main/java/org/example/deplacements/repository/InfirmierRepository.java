package org.example.deplacements.repository;

import org.example.deplacements.dto.DeplacementDetailsDTO;
import org.example.deplacements.entity.Infirmier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class InfirmierRepository {

    /*
    declaring the webclient singleton as an attribute then inject it inside the repository
    through the constructor
    Since the module has 2 webclients, have to qualify them
     */
    private WebClient webClient;

    public InfirmierRepository(@Qualifier("webClientInfirmiers") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * queries the infirmier microservice for details on a single nurse
     * @param id String pointing to the nurse inside the db
     * @return a Mono object of the infirmier entity
     */
    public Mono<Infirmier> getInfirmierDetails(String id){
        return webClient.get()
                .uri("/infirmiers/" + id)
                .retrieve()
                .bodyToMono(Infirmier.class);
    }
}
