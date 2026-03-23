package com.example.logisight.users.model;

import com.example.logisight.orders.dto.OrderResponseDto;

import java.util.List;

public record UserFullResponse(
        Long id,
        String username,
        List<OrderResponseDto> orders
) {
}
