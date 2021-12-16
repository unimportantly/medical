package fr.microservices.patients.repository;

import fr.microservices.patients.entity.InfirmierDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class InfirmierRepository {

    private WebClient webClient;

    public InfirmierRepository(WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<InfirmierDTO> getInfirmier(String id){
        Mono<InfirmierDTO> infirmier = webClient.get()
                .uri("/infirmiers/" + id)
                .retrieve()
                .bodyToMono(InfirmierDTO.class);
        return infirmier;
    }

}
