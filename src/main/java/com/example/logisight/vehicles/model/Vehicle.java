package com.example.logisight.vehicles.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicles")
@Accessors(chain = true)
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
}
