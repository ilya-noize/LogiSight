package com.example.logisight.cargo.service;

import com.example.logisight.cargo.dto.CargoCurrentLocationRequestDto;
import com.example.logisight.cargo.dto.CargoRequestDto;
import com.example.logisight.cargo.dto.CargoResponseDto;
import com.example.logisight.cargo.dto.CargoUpdateRequestDto;
import com.example.logisight.cargo.exception.CargoNotFoundException;
import com.example.logisight.cargo.exception.CargoStatusInvalidException;
import com.example.logisight.cargo.mapper.CargoMapper;
import com.example.logisight.cargo.model.Cargo;
import com.example.logisight.cargo.model.CargoStatus;
import com.example.logisight.cargo.repo.CargoRepo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.example.logisight.cargo.service.TrackNumberGenerator.generateTrackNumber;

@Service
public class CargoServiceImpl implements CargoService {
    private static final String CARGO_N_NOT_FOUND = "Груз %d не найден";
    private final CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public CargoResponseDto createCargo(CargoRequestDto cargoRequestDto) {
        String trackNumber;
        do {
            trackNumber = generateTrackNumber();
        } while (cargoRepo.existsByTrackingNumberIgnoreCase(trackNumber));

        Cargo cargo = CargoMapper.INSTANCE.toEntity(cargoRequestDto);
        cargo.setStatus(CargoStatus.CREATED);
        cargo.setTrackingNumber(trackNumber);
        cargoRepo.save(cargo);

        return CargoMapper.INSTANCE.toDTO(cargo);
    }

    @Override
    public CargoResponseDto updateCargo(Long id, CargoUpdateRequestDto request) {
        Cargo existingCargo = cargoRepo.findById(id)
                .orElseThrow(() -> new CargoNotFoundException(String.format(CARGO_N_NOT_FOUND, id)));

        if (request.description() != null) {
            existingCargo.setDescription(request.description());
        }
        if (request.weight() != null) {
            existingCargo.setWeight(request.weight());
        }
        if (request.volume() != null) {
            existingCargo.setVolume(request.volume());
        }
        if (request.recipient() != null) {
            existingCargo.setRecipient(request.recipient());
        }
        if (request.currentLocation() != null) {
            existingCargo.setCurrentLocation(request.currentLocation());
        }
        if (request.deliveryAddress() != null) {
            existingCargo.setDeliveryAddress(request.deliveryAddress());
        }
        if (request.deliveryDate() != null) {
            existingCargo.setDeliveryDate(request.deliveryDate());
        }
        if (request.status() != null) {
            existingCargo.setStatus(request.status());
        }
        if (request.fragile() != null) {
            existingCargo.setFragile(request.fragile());
        }
        if (request.specialInstructions() != null) {
            existingCargo.setSpecialInstructions(request.specialInstructions());
        }
        return CargoMapper.INSTANCE.toDTO(cargoRepo.save(existingCargo));
    }

    @Override
    public CargoResponseDto updateCargoStatus(Long id, String status) throws CargoStatusInvalidException {

        Cargo existingCargo = cargoRepo.findById(id)
                .orElseThrow(() -> new CargoNotFoundException(String.format(CARGO_N_NOT_FOUND, id)));
        if (status != null) {
            try {
                CargoStatus cargoStatus = CargoStatus.valueOf(status);
                existingCargo.setStatus(cargoStatus);
            } catch (IllegalArgumentException e) {
                throw new CargoStatusInvalidException(String.format("Статус груза %d не определён", id));
            }
        }
        return CargoMapper.INSTANCE.toDTO(cargoRepo.save(existingCargo));
    }

    @Override
    public CargoResponseDto updateCargoCurrentLocation(CargoCurrentLocationRequestDto request) {
        Cargo existingCargo = cargoRepo.findById(request.id())
                .orElseThrow(() -> new CargoNotFoundException(String.format(CARGO_N_NOT_FOUND, request.id())));
        if (request.currentLocation() != null) {
            existingCargo.setCurrentLocation(request.currentLocation());
        }
        return CargoMapper.INSTANCE.toDTO(cargoRepo.save(existingCargo));
    }

    @Override
    public CargoResponseDto getCargo(Long id) {
        Cargo existingCargo = cargoRepo.findById(id)
                .orElseThrow(() -> new CargoNotFoundException(String.format(CARGO_N_NOT_FOUND, id)));
        return CargoMapper.INSTANCE.toDTO(existingCargo);
    }

    @Override
    public List<CargoResponseDto> getAllCargo() {
        List<Cargo> existingAllCargo = cargoRepo.findAll();
        if (existingAllCargo.isEmpty()) {
            return Collections.emptyList();
        }
        return existingAllCargo.stream().map(CargoMapper.INSTANCE::toDTO).toList();
    }

    @Override
    public void deleteCargo(Long id) {
        Cargo existingCargo = cargoRepo.findById(id)
                .orElseThrow(() -> new CargoNotFoundException(String.format(CARGO_N_NOT_FOUND, id)));
        cargoRepo.delete(existingCargo);
    }
}
