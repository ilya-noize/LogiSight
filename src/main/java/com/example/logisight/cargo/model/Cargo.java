package com.example.logisight.cargo.model;

import com.example.logisight.orders.model.Order;
import com.example.logisight.trackingpoints.model.TrackingPoint;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

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

    // Связь с заказом
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

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
    private LocalDate pickupDate;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrackingPoint> trackingPoints;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CargoStatus status = CargoStatus.CREATED;

    @Column(unique = true, nullable = false)
    private String trackingNumber;

    @Column(nullable = false)
    private Boolean fragile;

    @Column(nullable = false)
    private String specialInstructions;
}
