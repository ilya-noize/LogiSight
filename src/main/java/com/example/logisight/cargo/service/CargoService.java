package com.example.logisight.cargo.service;

import com.example.logisight.cargo.dto.CargoCurrentLocationRequestDto;
import com.example.logisight.cargo.dto.CargoRequestDto;
import com.example.logisight.cargo.dto.CargoResponseDto;
import com.example.logisight.cargo.dto.CargoUpdateRequestDto;
import com.example.logisight.cargo.exception.CargoStatusInvalidException;

import java.util.List;

public interface CargoService {

    CargoResponseDto createCargo(CargoRequestDto cargoRequestDto);

    CargoResponseDto updateCargo(Long id, CargoUpdateRequestDto cargoUpdateRequestDto);

    CargoResponseDto updateCargoStatus(Long id, String status) throws CargoStatusInvalidException;

    CargoResponseDto updateCargoCurrentLocation(CargoCurrentLocationRequestDto cargoCurrentLocationRequestDto);

    CargoResponseDto getCargo(Long id);

    List<CargoResponseDto> getAllCargo();

    void deleteCargo(Long id);
}
