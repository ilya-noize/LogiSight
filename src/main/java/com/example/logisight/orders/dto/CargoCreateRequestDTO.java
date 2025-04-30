package com.example.logisight.orders.dto;

import com.example.logisight.orders.annotaion.valid.dimension.ValidDimensions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// CargoCreateRequestDTO
public class CargoCreateRequestDTO {
    @NotBlank(message = "Наименование груза не может быть пустым")
    private String description;

    @NotNull(message = "Вес не может быть пустым")
    @Positive(message = "Вес должен быть положительным числом")
    private Double weight;

    @NotNull(message = "Размеры не могут быть пустыми")
    @ValidDimensions(message = "Размеры должны быть в формате ШИРИНАxВЫСОТАxДЛИНА. " +
            "Например: \"10х20х30\". Измерения производить в сантиметрах.")
    private String dimensions;

    @NotBlank(message = "Адрес забора не может быть пустым")
    private String pickupAddress;

    @NotBlank(message = "Адрес доставки не может быть пустым")
    private String deliveryAddress;
}
