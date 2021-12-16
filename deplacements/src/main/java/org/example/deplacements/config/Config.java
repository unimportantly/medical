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

    @Value("${environnement.uriPatients}")
    private String urlPatients;

    @Value("${environnement.uriInfirmiers}")
    private String urlInfirmiers;

    @Bean
    public WebClient webClientPatients(){
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(urlPatients)
                .build();
    }

    @Bean
    public WebClient webClientInfirmiers(){
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(urlInfirmiers)
                .build();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
