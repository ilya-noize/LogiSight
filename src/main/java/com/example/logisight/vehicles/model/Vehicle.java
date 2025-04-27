package com.example.logisight.vehicles.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <h1>Класс "Транспортное средство"</h1>
 * <p>
 * Класс полностью готов к использованию в системе управления транспортом
 * и может быть расширен дополнительными полями и методами в зависимости от
 * конкретных требований бизнеса.
 * </p>
 * <p>
 * Этот класс описывает транспортное средство для доставки грузов и включает:
 * </p>
 *
 * Базовые характеристики:
 * <ul>
 *     <li>Идентификатор</li>
 *     <li>Название транспортного средства</li>
 *     <li>Регистрационный номер</li>
 *     <li>Тип транспортного средства</li>
 *     <li>Грузоподъемность</li>
 *     <li>Текущая загрузка</li>
 * </ul>
 * <p>
 * Эксплуатационные характеристики:
 * <ul>
 *     <li>Расход топлива</li>
 *     <li>Скорость</li>
 *     <li>Стоимость за километр</li>
 * </ul>
 * <p>
 * Состояние:
 * <ul>
 *     <li>Дата последнего ТО</li>
 *     <li>Доступность для использования</li>
 *     <li></li>
 * </ul>
 * <p>
 * Методы расчета:
 * <ul>
 *     <li>Время в пути</li>
 *     <li>Стоимость поездки</li>
 *     <li>Расход топлива</li>
 * </ul>
 */
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private String vehicleType; // например: грузовик, фургон, прицеп

    @Column(nullable = false)
    private double maxLoadCapacity; // максимальная грузоподъемность

    @Column(nullable = false)
    private double currentLoad; // текущая загрузка

    @Column(nullable = false)
    private double fuelConsumption; // расход топлива на 100 км

    @Column(nullable = false)
    private double speed; // средняя скорость в км/ч

    @Column(nullable = false)
    private BigDecimal costPerKm; // стоимость за километр пробега

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date lastMaintenance; // дата последнего ТО

    @Column(nullable = false)
    private boolean isAvailable; // доступно для использования

    // конструкторы
    public Vehicle() {
    }

    public Vehicle(String name, String registrationNumber, String vehicleType,
                   double maxLoadCapacity, double speed, BigDecimal costPerKm) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.maxLoadCapacity = maxLoadCapacity;
        this.speed = speed;
        this.costPerKm = costPerKm;
        this.isAvailable = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    public void setMaxLoadCapacity(double maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public BigDecimal getCostPerKm() {
        return costPerKm;
    }

    public void setCostPerKm(BigDecimal costPerKm) {
        this.costPerKm = costPerKm;
    }

    public Date getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(Date lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // методы для расчета
    public double calculateTravelTime(double distance) {
        return distance / speed;
    }

    public BigDecimal calculateTravelCost(double distance) {
        return costPerKm.multiply(BigDecimal.valueOf(distance));
    }

    public double calculateFuelConsumption(double distance) {
        return (distance / 100) * fuelConsumption;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", maxLoadCapacity=" + maxLoadCapacity +
                ", currentLoad=" + currentLoad +
                ", fuelConsumption=" + fuelConsumption +
                ", speed=" + speed +
                ", costPerKm=" + costPerKm +
                ", lastMaintenance=" + lastMaintenance +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
