//package fr.microservices.patients;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//
//import java.awt.*;
//import java.net.http.HttpHeaders;
//
//public class Config {
//
//    public static final Logger LOGGER = loggerFactory.getLogger(WebConfig.class);
//    // -> this allows us to log data when we connect thanks to the webclient
//
//
//    @Value("${demo.uri}")
//    // stores the environment variable uri into a usable String
//    private String URL;
//
//
//    @Bean
//    /*
//    this will allow us to use either synchronous or asynchronous requests
//    logger & filter & shit -> to change the way we treat the info we get?
//     */
//    public WebClient webClient(){
//        return webClient.builder()
//                // specifies headers(idfk) -> so wait, we'll give you json, basically
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                // add a logger to catch the request url -> unnecessary
//                .filter((request, next) -> {
//                    LOGGER.warn("URI: {}", request.url()); // -> will print this
//                    return next.exchange(request);
//        })
//        // add the default uri
//        .baseURL(URL).build();
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
