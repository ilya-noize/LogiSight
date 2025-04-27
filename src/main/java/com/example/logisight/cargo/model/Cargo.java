package com.example.logisight.cargo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double weight; // в килограммах

    @Column(nullable = false)
    private Double volume; // в кубических метрах

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false)
    private String pickupAddress;

    @Column(nullable = false)
    private String currentLocation;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private LocalDate pickupDate;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    @Column(nullable = false)
    private CargoStatus status;

    @Column(nullable = false)
    private String trackingNumber;

    @Column(nullable = false)
    private Boolean fragile;

    @Column(nullable = false)
    private String specialInstructions;
}
