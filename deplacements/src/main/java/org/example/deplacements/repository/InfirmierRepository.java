package org.example.deplacements.repository;

import org.example.deplacements.dto.DeplacementDetailsDTO;
import org.example.deplacements.entity.Infirmier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class InfirmierRepository {

    private WebClient webClient;

    public InfirmierRepository(@Qualifier("webClientInfirmiers") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Infirmier> getInfirmierDetails(String id){
        return webClient.get()
                .uri("/infirmiers/" + id)
                .retrieve()
                .bodyToMono(Infirmier.class);
    }
}
