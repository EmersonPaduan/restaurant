package br.paduan.waiterservice.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import br.paduan.waiterservice.model.Order;
import reactor.core.publisher.Mono;

@RestController
public class WaiterController {

    Logger log = Logger.getLogger(this.getClass().getName());

    @PostMapping("/waiter-blocking")
    public Order getOrderBlocking(@RequestBody Order newOrder) {
        log.info("Início do trabalho do garçom");

        RestTemplate restTemplate = new RestTemplate();
        Order resultOrder = restTemplate.postForObject("http://localhost:8080/order", newOrder, Order.class);

        log.info(resultOrder.toString());

        log.info("Termino do trabalho do garçom");

        return resultOrder;
    }

    @PostMapping("/waiter-non-blocking")
    public Mono<Order> getOrderNonBlocking(@RequestBody Order newOrder) {
        log.info("Início do trabalho do garçom");

        WebClient webClient = WebClient.create("http://localhost:8080/order");

        Mono<Order> resultOrder = webClient.post()
                .body(Mono.just(newOrder), Order.class)
                .retrieve()
                .bodyToMono(Order.class);

        resultOrder.subscribe(order -> log.info(order.toString()));

        log.info("Termino do trabalho do garçom");

        return resultOrder;
    }

}
