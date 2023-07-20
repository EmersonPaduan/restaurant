package br.paduan.waiterservice.model;

public record Order(int tableNumber, String details, Status status) {

}
