package org.example.deplacements.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    /*
    makes use of the variable stored in the application.yml to define a url
    for when we want to connect to the patient microservice
     */
    @Value("${environnement.uriPatients}")
    private String urlPatients;

    /*
    makes use of the variable stored in the application.yml to define a url
    for when we want to connect to the infirmier microservice
     */
    @Value("${environnement.uriInfirmiers}")
    private String urlInfirmiers;

    /**
     * creates a webclient object to be used inside the module that'll allow this microservice to
     * communicate with others(here the patient one)
     * @return a webclient object
     */
    @Bean
    public WebClient webClientPatients(){
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(urlPatients)
                .build();
    }

    /**
     * creates a webclient object to be used inside the module that'll allow this microservice to
     * communicate with others (here the infirmier one)
     * @return a webclient object
     */
    @Bean
    public WebClient webClientInfirmiers(){
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(urlInfirmiers)
                .build();
    }

    /**
     * creates a mapper object to be used inside the module that'll allow us to transform
     * optionals into DTOs
     * @return a mapper object
     */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
