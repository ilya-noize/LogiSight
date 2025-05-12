package com.example.logisight.trackingpoints.mapper;

import com.example.logisight.trackingpoints.dto.TrackingPointDto;
import com.example.logisight.trackingpoints.model.TrackingPoint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TrackingPointMapper {
    TrackingPointMapper INSTANCE = Mappers.getMapper(TrackingPointMapper.class);

    TrackingPointDto toDto(TrackingPoint trackingPoint);

    TrackingPoint toEntity(TrackingPointDto trackingPointDto);
}
