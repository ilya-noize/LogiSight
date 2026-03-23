package com.example.logisight.cargo.service;

import com.example.logisight.cargo.dto.CargoRequestDto;
import com.example.logisight.cargo.dto.CargoResponseDto;
import com.example.logisight.cargo.dto.CargoUpdateRequestDto;
import com.example.logisight.cargo.exception.CargoStatusInvalidException;

import java.util.List;

public interface CargoService {
    String CARGO_N_NOT_FOUND = "Груз с ID %d не найден";

    CargoResponseDto createCargo(CargoRequestDto cargoRequestDto);

    CargoResponseDto updateCargo(Long id, CargoUpdateRequestDto cargoUpdateRequestDto);

    CargoResponseDto updateCargoStatus(Long id, String status) throws CargoStatusInvalidException;

    CargoResponseDto updateCargoCurrentLocation(Long id, Long pointId);

    CargoResponseDto getCargo(Long id);

    List<CargoResponseDto> getAllCargo();

    void deleteCargo(Long id);
}
