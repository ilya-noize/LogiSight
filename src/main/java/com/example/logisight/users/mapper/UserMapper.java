package com.example.logisight.users.mapper;

import com.example.logisight.cargo.mapper.CargoMapper;
import com.example.logisight.orders.mapper.OrderMapper;
import com.example.logisight.trackingpoints.mapper.TrackingPointMapper;
import com.example.logisight.users.dto.UserFullResponseDto;
import com.example.logisight.users.dto.UserRequestDto;
import com.example.logisight.users.dto.UserResponseDto;
import com.example.logisight.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        uses = {OrderMapper.class, CargoMapper.class, TrackingPointMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto toDto (User user);

    UserFullResponseDto toFullDto(User user);

    User toUser(UserRequestDto userDto);
}
