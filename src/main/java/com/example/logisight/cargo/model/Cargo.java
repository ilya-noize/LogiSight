package com.example.logisight.cargo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Builder
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

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", volume=" + volume +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", pickupDate=" + pickupDate +
                ", deliveryDate=" + deliveryDate +
                ", status=" + status +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", fragile=" + fragile +
                ", specialInstructions='" + specialInstructions + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(id, cargo.id) && Objects.equals(description, cargo.description) && Objects.equals(weight, cargo.weight) && Objects.equals(volume, cargo.volume) && Objects.equals(sender, cargo.sender) && Objects.equals(recipient, cargo.recipient) && Objects.equals(pickupAddress, cargo.pickupAddress) && Objects.equals(currentLocation, cargo.currentLocation) && Objects.equals(deliveryAddress, cargo.deliveryAddress) && Objects.equals(pickupDate, cargo.pickupDate) && Objects.equals(deliveryDate, cargo.deliveryDate) && status == cargo.status && Objects.equals(trackingNumber, cargo.trackingNumber) && Objects.equals(fragile, cargo.fragile) && Objects.equals(specialInstructions, cargo.specialInstructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, weight, volume, sender, recipient, pickupAddress, currentLocation, deliveryAddress, pickupDate, deliveryDate, status, trackingNumber, fragile, specialInstructions);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public CargoStatus getStatus() {
        return status;
    }

    public void setStatus(CargoStatus status) {
        this.status = status;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Boolean getFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}
