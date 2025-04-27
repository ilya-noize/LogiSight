package com.example.logisight.vehicles.service;

import com.example.logisight.vehicles.dto.VehicleRequestDto;
import com.example.logisight.vehicles.dto.VehicleResponseDto;
import com.example.logisight.vehicles.dto.VehicleUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
    VehicleResponseDto createVehicle(VehicleRequestDto vehicleRequestDto);

    VehicleResponseDto updateVehicle(Long id, VehicleUpdateRequestDto vehicleUpdateRequestDto);

    VehicleResponseDto getVehicleById(Long id);

    List<VehicleResponseDto> getAllVehicles();

    void deleteVehicle(Long id);

    List<VehicleResponseDto> getAvailableVehicles();

    List<VehicleResponseDto> getVehiclesByType(String vehicleType);

    VehicleResponseDto updateAvailability(Long id);
}
