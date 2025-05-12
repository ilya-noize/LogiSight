package com.example.logisight.orders.dto;

import com.example.logisight.cargo.dto.CargoResponseDto;
import com.example.logisight.users.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private UserResponseDto user;
    private List<CargoResponseDto> weight;
    private String status;
    private LocalDateTime createAt;
}
