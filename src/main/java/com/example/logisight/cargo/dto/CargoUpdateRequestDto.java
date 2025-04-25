package com.example.logisight.cargo.dto;

import com.example.logisight.cargo.model.CargoStatus;

import java.time.LocalDate;

public record CargoUpdateRequestDto(
        String description,
        Double weight,
        Double volume,
        String recipient,
        String currentLocation,
        String deliveryAddress,
        LocalDate deliveryDate,
        CargoStatus status,
        Boolean fragile,
        String specialInstructions) {
}
