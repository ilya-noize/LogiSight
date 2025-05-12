package com.example.logisight.cargo.dto;

import com.example.logisight.cargo.model.CargoStatus;
import com.example.logisight.trackingpoints.dto.TrackingPointDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class CargoResponseDto {
    private Long id;
    private String description;
    private Double weight;
    private Double volume;
    private String sender;
    private String recipient;
    private String pickupAddress;
    private List<TrackingPointDto> trackingPoints;
    private String deliveryAddress;
    private LocalDate pickupDate;
    private LocalDate deliveryDate;
    private CargoStatus status;
    private String trackingNumber;
    private Boolean fragile;
    private String specialInstructions;
}
