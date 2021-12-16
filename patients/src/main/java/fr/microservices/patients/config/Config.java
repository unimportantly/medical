package fr.microservices.patients.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    // makes use of the variable stored in the application.yml to define a url
    @Value("${environnement.uri}")
    private String URL;

    /**
     * creates a webclient object to be used inside the module that'll allow this microservice to
     * communicate with others
     * @return a webclient object
     */
    @Bean
    public WebClient webClient(){
        return WebClient.builder() // use builder to give the base object some modifications, otherwise, use create()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(URL)
                .build();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
