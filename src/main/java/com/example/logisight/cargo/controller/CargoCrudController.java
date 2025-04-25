package com.example.logisight.cargo.controller;

import com.example.logisight.cargo.dto.CargoRequestDto;
import com.example.logisight.cargo.dto.CargoResponseDto;
import com.example.logisight.cargo.dto.CargoUpdateRequestDto;
import com.example.logisight.cargo.service.CargoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cargo")
public class CargoCrudController {
    private final CargoService cargoService;

    public CargoCrudController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CargoResponseDto createCargo(@RequestBody CargoRequestDto cargoRequestDto) {
        return cargoService.createCargo(cargoRequestDto);
    }

    @PutMapping("/{id}")
    public CargoResponseDto updateCargo(@PathVariable Long id, @RequestBody CargoUpdateRequestDto cargoUpdateRequestDto) {
        return cargoService.updateCargo(id, cargoUpdateRequestDto);
    }

    @GetMapping("/{id}")
    public CargoResponseDto getCargo(@PathVariable Long id) {
        return cargoService.getCargo(id);
    }

    @GetMapping
    public List<CargoResponseDto> getAllCargo() {
        return cargoService.getAllCargo();
    }

    @DeleteMapping("/{id}")
    public void deleteCargo(@PathVariable Long id) {
        cargoService.deleteCargo(id);
    }
}
