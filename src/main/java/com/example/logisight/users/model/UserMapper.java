package com.example.logisight.users.model;

import com.example.logisight.cargo.mapper.CargoMapper;
import com.example.logisight.orders.mapper.OrderMapper;
import com.example.logisight.trackingpoints.mapper.TrackingPointMapper;
import com.example.logisight.users.db.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,
        uses = {OrderMapper.class, CargoMapper.class, TrackingPointMapper.class})
public interface UserMapper {

    UserEntity toEntity(User domain);

    User toDomain(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toDomain(SignUpRequest request);

    UserResponse toResponse(User user);
}
