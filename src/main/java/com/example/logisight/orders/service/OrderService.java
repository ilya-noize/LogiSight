package com.example.logisight.orders.service;

import com.example.logisight.orders.dto.CargoCreateRequestDTO;
import com.example.logisight.orders.dto.OrderResponseDto;
import com.example.logisight.orders.dto.OrderWithCargoRequestDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createCargo(OrderWithCargoRequestDto orderWithCargoRequestDto);

    OrderResponseDto updateCargoInOrder(Long id, List<CargoCreateRequestDTO> cargos);

    OrderResponseDto updateCargoStateForOrderById(Long id, Long cargoId, String status);

}
