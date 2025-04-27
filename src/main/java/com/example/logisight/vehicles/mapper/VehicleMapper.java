package com.example.logisight.vehicles.mapper;

import com.example.logisight.vehicles.dto.VehicleRequestDto;
import com.example.logisight.vehicles.dto.VehicleResponseDto;
import com.example.logisight.vehicles.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Vehicle toEntity(VehicleRequestDto vehicleRequestDto);

    VehicleResponseDto toDto(Vehicle vehicle);
}
