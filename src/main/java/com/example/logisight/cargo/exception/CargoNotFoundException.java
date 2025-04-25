package com.example.logisight.cargo.exception;

public class CargoNotFoundException extends RuntimeException {
    public CargoNotFoundException(String message) {
        super(message);
    }
}
