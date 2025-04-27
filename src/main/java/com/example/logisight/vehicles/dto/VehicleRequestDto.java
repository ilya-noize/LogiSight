package com.example.logisight.vehicles.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VehicleRequestDto {
    private String name;
    private String registrationNumber;
    private String vehicleType;
    private Double maxLoadCapacity;
    private Double currentLoad;
    private Double fuelConsumption;
    private Double speed;
    private BigDecimal costPerKm;
    private Date lastMaintenance;
    private Boolean isAvailable;
}
