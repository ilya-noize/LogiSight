package com.example.logisight.cargo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CargoRequestDto {
    private String description;
    private Double weight;
    private Double volume;
    private String sender;
    private String recipient;
    private String pickupAddress;
    private String currentLocation;
    private String deliveryAddress;
    private LocalDate pickupDate;
    private LocalDate deliveryDate;
    private Boolean fragile;
    private String specialInstructions;
}
