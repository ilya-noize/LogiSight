package com.example.logisight.cargo.dto;

import com.example.logisight.cargo.model.CargoStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CargoResponseDto {
    private Long id;
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
    private CargoStatus status;
    private String trackingNumber;
    private Boolean fragile;
    private String specialInstructions;
}
