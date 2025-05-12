package com.example.logisight.cargo.controller;

import com.example.logisight.cargo.dto.CargoResponseDto;
import com.example.logisight.cargo.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cargo")
@RequiredArgsConstructor
public class CargoTerminalController {
    private final CargoService cargoService;

    @PatchMapping("/{id}/{status}")
    public CargoResponseDto updateCargoStatus(@PathVariable Long id, @PathVariable String status) {
        return cargoService.updateCargoStatus(id, status);
    }

    @PatchMapping("/{id}/tracking/{pointId}")
    public CargoResponseDto updateCargoCurrentLocation(@PathVariable Long id, @PathVariable Long pointId) {
        return cargoService.updateCargoCurrentLocation(id, pointId);
    }
}
