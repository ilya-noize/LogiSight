package com.example.logisight.orders.dto;

import com.example.logisight.users.dto.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderWithCargoRequestDto(
        Long id,
        UserResponseDto user,
// todo: Добавление грузов после создания заказа
        List<CargoCreateRequestDTO> cargos,
        String status, // default = CREATED
        LocalDateTime createdAt) {

}

