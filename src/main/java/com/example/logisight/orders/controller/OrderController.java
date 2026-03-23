package com.example.logisight.orders.controller;

import com.example.logisight.orders.dto.CargoCreateRequestDTO;
import com.example.logisight.orders.dto.OrderResponseDto;
import com.example.logisight.orders.dto.OrderWithCargoRequestDto;
import com.example.logisight.orders.service.OrderService;
import com.example.logisight.users.annotation.ManagerAuthorization;
import com.example.logisight.users.annotation.OperatorAuthorization;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @OperatorAuthorization
    public OrderResponseDto createOrder(@RequestBody @Valid OrderWithCargoRequestDto orderWithCargoRequestDto) {
        return orderService.createCargo(orderWithCargoRequestDto);
    }

    @PatchMapping("/{id}/cargo")
    @OperatorAuthorization
    public OrderResponseDto updateCargoInOrder(@PathVariable Long id, @RequestBody List<CargoCreateRequestDTO> cargos) {
        return orderService.updateCargoInOrder(id, cargos);
    }

    @PatchMapping("/{id}/cargo/{cargoId}/status/{status}")
    @ManagerAuthorization
    public OrderResponseDto updateCargoStateForOrderById (
            @PathVariable Long id,
            @PathVariable Long cargoId,
            @PathVariable String status) {
        return orderService.updateCargoStateForOrderById(id, cargoId, status);
    }
}
