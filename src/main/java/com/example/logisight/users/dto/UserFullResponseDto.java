package com.example.logisight.users.dto;


import com.example.logisight.orders.dto.OrderResponseDto;

import java.util.List;

public record UserFullResponseDto(
        Long id,
        String username,
        List<OrderResponseDto> orders) {
}
