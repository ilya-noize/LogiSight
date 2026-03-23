package com.example.logisight.orders.mapper;

import com.example.logisight.cargo.mapper.CargoMapper;
import com.example.logisight.orders.dto.OrderResponseDto;
import com.example.logisight.orders.dto.OrderWithCargoRequestDto;
import com.example.logisight.orders.model.Order;
import com.example.logisight.users.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        uses = {CargoMapper.class, UserMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponseDto toDto(Order order);

    Order toEntity(OrderWithCargoRequestDto orderWithCargoRequestDto);
}
