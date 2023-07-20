package br.paduan.chefservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.paduan.chefservice.model.Order;
import br.paduan.chefservice.model.Status;

@RestController
public class ChefController {

    @PostMapping("/order")
    public Order getOrder(@RequestBody Order newOrder) throws InterruptedException {
        Thread.sleep(3000);
        Order order = new Order(newOrder.tableNumber(), newOrder.details(), Status.COMPLETED);
        return order;
    }
}
