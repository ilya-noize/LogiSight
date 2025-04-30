package com.example.logisight.orders.service;

import com.example.logisight.orders.dto.CargoCreateRequestDTO;
import com.example.logisight.orders.dto.OrderResponseDto;
import com.example.logisight.orders.dto.OrderWithCargoRequestDto;
import com.example.logisight.orders.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDto createCargo(OrderWithCargoRequestDto orderWithCargoRequestDto) {

        return null;
    }

    @Override
    public OrderResponseDto updateCargoInOrder(Long id, List<CargoCreateRequestDTO> cargos) {
        return null;
    }

    @Override
    public OrderResponseDto updateCargoStateForOrderById(Long id, Long cargoId, String status) {
        return null;
    }

    // Метод для расчета примерной стоимости доставки
    public double calculateEstimatedCost() {
        double weight = 0;
        double baseCost = 500; // todo: базовая стоимость / прейскурант в БД в зависимости от зон доставки
        double weightCost = weight * 100; // todo: стоимость за кг / прейскурант в БД в зависимости от веса груза
        double distanceCost = 2000; // todo: примерная стоимость за расстояние / прейскурант в БД в зависимости от зон доставки
        return baseCost + weightCost + distanceCost;
    }
}
