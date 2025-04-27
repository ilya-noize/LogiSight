package com.example.logisight.trackingpoints.dto;

import com.example.logisight.trackingpoints.model.TrackingPoint;
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

    @NotNull(message = "Широта обязательна")
    private Double latitude;

    @NotNull(message = "Долгота обязательна")
    private Double longitude;

    private boolean active;

    // Конструкторы для конвертации из/в entity
    public static TrackingPointDto fromEntity(TrackingPoint entity) {
        return new TrackingPointDto(
                entity.getId(),
                entity.getName(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.isActive()
        );
    }

    public TrackingPoint toEntity() {
        TrackingPoint entity = new TrackingPoint();
        entity.setId(id);
        entity.setName(name);
        entity.setLatitude(latitude);
        entity.setLongitude(longitude);
        entity.setActive(active);
        return entity;
    }
}
