package br.paduan.chefservice.model;

public record Order(int tableNumber, String details, Status status) {

}
