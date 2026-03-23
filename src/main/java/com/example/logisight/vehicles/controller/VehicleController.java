package com.example.logisight.vehicles.controller;

import com.example.logisight.vehicles.dto.VehicleRequestDto;
import com.example.logisight.vehicles.dto.VehicleResponseDto;
import com.example.logisight.vehicles.dto.VehicleUpdateRequestDto;
import com.example.logisight.vehicles.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    // Получение списка всех транспортных средств
    @GetMapping
    public List<VehicleResponseDto> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // Получение транспортного средства по ID
    @GetMapping("/{id}")
    public VehicleResponseDto getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    // Создание нового транспортного средства
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponseDto createVehicle(@RequestBody VehicleRequestDto vehicleRequestDto) {
        return vehicleService.createVehicle(vehicleRequestDto);
    }

    // Обновление информации о транспортном средстве
    @PutMapping("/{id}")
    public VehicleResponseDto updateVehicle(@PathVariable Long id, @RequestBody VehicleUpdateRequestDto vehicleUpdateRequestDto) {
        return vehicleService.updateVehicle(id, vehicleUpdateRequestDto);
    }

    // Удаление транспортного средства
    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

    // Фильтрация по доступности
    @GetMapping("/available")
    public List<VehicleResponseDto> getAvailableVehicles() {
        return vehicleService.getAvailableVehicles();
    }

    // Фильтрация по типу транспортного средства
    @GetMapping("/type/{vehicleType}")
    public List<VehicleResponseDto> getVehiclesByType(@PathVariable String vehicleType) {
        return vehicleService.getVehiclesByType(vehicleType);
    }

    // Обновление статуса доступности
    @PatchMapping("/{id}/availability")
    public VehicleResponseDto updateAvailability(@PathVariable Long id) {
        return vehicleService.updateAvailability(id);
    }
}
