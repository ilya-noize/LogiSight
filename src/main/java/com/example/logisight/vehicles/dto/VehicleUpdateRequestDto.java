package com.example.logisight.vehicles.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

public class VehicleUpdateRequestDto {
    @Getter
    private String name;
    @Getter
    private String registrationNumber;
    @Getter
    private String vehicleType;
    @Getter
    private Double maxLoadCapacity;
    @Getter
    private Double currentLoad;
    @Getter
    private Double fuelConsumption;
    @Getter
    private Double speed;
    @Getter
    private BigDecimal costPerKm;
    @Getter
    private Date lastMaintenance;
    @Getter
    private Boolean isAvailable;
}
