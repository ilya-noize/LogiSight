package com.example.logisight.trackingpoints.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackingPointDto {

    private Long id;

    @NotBlank(message = "Название точки обязательно")
    private String name;

    @NotNull
    private String description;

    @NotNull(message = "Широта обязательна")
    private Double latitude;

    @NotNull(message = "Долгота обязательна")
    private Double longitude;

    private boolean active;
}
