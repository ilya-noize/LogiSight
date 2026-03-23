package com.example.logisight.vehicles.service;

import com.example.logisight.vehicles.dto.VehicleRequestDto;
import com.example.logisight.vehicles.dto.VehicleResponseDto;
import com.example.logisight.vehicles.dto.VehicleUpdateRequestDto;
import com.example.logisight.vehicles.exception.VehicleInvalidException;
import com.example.logisight.vehicles.exception.VehicleNotFoundException;
import com.example.logisight.vehicles.mapper.VehicleMapper;
import com.example.logisight.vehicles.model.Vehicle;
import com.example.logisight.vehicles.repo.VehicleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiclesServiceImpl implements VehicleService {
    private static final String VEHICLE_NOT_FOUND = "Транспортное средство не найдено с ID: %d";
    private static final VehicleMapper MAPPER = VehicleMapper.INSTANCE;
    private final VehicleRepo vehicleRepository;

    @Override
    public VehicleResponseDto createVehicle(VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle = MAPPER.toEntity(vehicleRequestDto);
        return MAPPER.toDto(vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleResponseDto updateVehicle(Long id, VehicleUpdateRequestDto vehicleUpdateRequestDto) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(String.format(VEHICLE_NOT_FOUND, id)));

        if (vehicleUpdateRequestDto.getName() != null) {
            vehicle.setName(vehicleUpdateRequestDto.getName());
        }
        if (vehicleUpdateRequestDto.getRegistrationNumber() != null) {
            vehicle.setRegistrationNumber(vehicleUpdateRequestDto.getRegistrationNumber());
        }
        if (vehicleUpdateRequestDto.getVehicleType() != null) {
            vehicle.setVehicleType(vehicleUpdateRequestDto.getVehicleType());
        }
        if (vehicleUpdateRequestDto.getMaxLoadCapacity() != null) {
            vehicle.setMaxLoadCapacity(vehicleUpdateRequestDto.getMaxLoadCapacity());
        }
        if (vehicleUpdateRequestDto.getCurrentLoad() != null) {
            vehicle.setCurrentLoad(vehicleUpdateRequestDto.getCurrentLoad());
        }
        if (vehicleUpdateRequestDto.getFuelConsumption() != null) {
            vehicle.setFuelConsumption(vehicleUpdateRequestDto.getFuelConsumption());
        }
        if (vehicleUpdateRequestDto.getSpeed() != null) {
            vehicle.setSpeed(vehicleUpdateRequestDto.getSpeed());
        }
        if (vehicleUpdateRequestDto.getCostPerKm() != null) {
            vehicle.setCostPerKm(vehicleUpdateRequestDto.getCostPerKm());
        }
        if (vehicleUpdateRequestDto.getLastMaintenance() != null) {
            vehicle.setLastMaintenance(vehicleUpdateRequestDto.getLastMaintenance());
        }
        if (vehicleUpdateRequestDto.getIsAvailable() != null) {
            vehicle.setAvailable(vehicleUpdateRequestDto.getIsAvailable());
        }

        if (vehicle.getCurrentLoad() > vehicle.getMaxLoadCapacity()) {
            throw new VehicleInvalidException("Текущая нагрузка превышает максимальную грузоподъемность");
        }
        if (vehicle.getFuelConsumption() < 0) {
            throw new VehicleInvalidException("Расход топлива не может быть отрицательным");
        }
        if (vehicle.getSpeed() < 0) {
            throw new VehicleInvalidException("Скорость не может быть отрицательной");
        }
        if (vehicle.getCostPerKm().compareTo(BigDecimal.ZERO) < 0) {
            throw new VehicleInvalidException("Стоимость за км не может быть отрицательной");
        }

        return MAPPER.toDto(vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleResponseDto getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
            .orElseThrow(() -> new VehicleNotFoundException(String.format(VEHICLE_NOT_FOUND, id)));
        return MAPPER.toDto(vehicle);
    }

    @Override
    public List<VehicleResponseDto> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (vehicles.isEmpty())
            return Collections.emptyList();
        return vehicles.stream().map(MAPPER::toDto).toList();
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(String.format(VEHICLE_NOT_FOUND, id)));
        vehicleRepository.delete(vehicle);
    }

    @Override
    public List<VehicleResponseDto> getAvailableVehicles() {
        List<Vehicle> availableVehicles = vehicleRepository.findByIsAvailableTrue();
        if (availableVehicles.isEmpty())
            return Collections.emptyList();
        return availableVehicles.stream().map(MAPPER::toDto).toList();
    }

    @Override
    public List<VehicleResponseDto> getVehiclesByType(String vehicleType) {
        List<Vehicle> vehiclesByType = vehicleRepository.findByVehicleTypeIgnoreCase(vehicleType);
        if (vehiclesByType.isEmpty())
            return Collections.emptyList();
        return vehiclesByType.stream().map(MAPPER::toDto).toList();
    }

    @Override
    public VehicleResponseDto updateAvailability(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(String.format(VEHICLE_NOT_FOUND, id)));
        vehicle.setAvailable(!vehicle.isAvailable());
        return MAPPER.toDto(vehicleRepository.save(vehicle));
    }
}
