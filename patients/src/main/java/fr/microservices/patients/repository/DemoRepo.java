//package fr.microservices.patients.repository;
//
//import org.springframework.web.reactive.function.client.WebClient;
//
//public class DemoRepo {
//
//    private WebClient webClient;
//
//    public DemoRepo(WebClient webClient) {
//        this.webClient = webClient;
//    }
//
//    public Flux<T> getT(){
//        // flux means it's an asynchronous
//        Flux<T> t = webClient.get()// -> type of request
//                .uri("/posts") // -> add any needed precision to the uri already provided in the env variables
//                .retrieve() // -> gets the data
//                .bodyToFlux(T.class); // -> map the result to a given class
//        return t; // -> a flux is returned, not a final element like a list
//        // so when it's called back from the controller to get all posts, it won't need
//        // to be stored in a list
//        /*
//        @GetMapping()
//        public Flux<T> getTs(){
//        return repository.getT();
//         */
//    }
//
//    public Mono<T> getTmono() {
//        // synchronous request
//        Mono<T> t = webClient.get() // -> same same
//                .uri("/posts")
//                .retrieve()
//                .bodyToMono(T.class)
//                .block(); // -> will block the request & make it synchronous
//        return t;
//    }
//}
