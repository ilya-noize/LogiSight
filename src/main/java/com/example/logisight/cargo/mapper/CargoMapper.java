package com.example.logisight.cargo.mapper;

import com.example.logisight.cargo.dto.CargoRequestDto;
import com.example.logisight.cargo.dto.CargoResponseDto;
import com.example.logisight.cargo.model.Cargo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CargoMapper {
    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    CargoResponseDto toDTO(Cargo entity);

    @Mapping(target = "trackingNumber", ignore = true)
    Cargo toEntity(CargoRequestDto dto);
}
