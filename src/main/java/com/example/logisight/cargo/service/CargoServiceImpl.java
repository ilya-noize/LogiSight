package com.example.logisight.cargo.service;

import com.example.logisight.cargo.dto.CargoRequestDto;
import com.example.logisight.cargo.dto.CargoResponseDto;
import com.example.logisight.cargo.dto.CargoUpdateRequestDto;
import com.example.logisight.cargo.exception.CargoNotFoundException;
import com.example.logisight.cargo.exception.CargoStatusInvalidException;
import com.example.logisight.cargo.mapper.CargoMapper;
import com.example.logisight.cargo.model.Cargo;
import com.example.logisight.cargo.model.CargoStatus;
import com.example.logisight.cargo.repo.CargoRepo;
import com.example.logisight.trackingpoints.exception.TrackingPointNotFoundException;
import com.example.logisight.trackingpoints.model.TrackingPoint;
import com.example.logisight.trackingpoints.repo.TrackingPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.example.logisight.cargo.service.TrackNumberGenerator.generateTrackNumber;
import static com.example.logisight.trackingpoints.service.TrackingPointServiceImpl.TRACKING_POINT_N_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {
    private static final CargoMapper MAPPER = CargoMapper.INSTANCE;
    private final CargoRepo cargoRepo;
    private final TrackingPointRepository trackingPointRepo;

    @Override
    public CargoResponseDto createCargo(CargoRequestDto cargoRequestDto) {
        String trackNumber;
        do {
            trackNumber = generateTrackNumber();
        } while (cargoRepo.existsByTrackingNumberIgnoreCase(trackNumber));

        Cargo cargo = MAPPER.toEntity(cargoRequestDto);
        cargo.setStatus(CargoStatus.CREATED);
        cargo.setTrackingNumber(trackNumber);
        cargoRepo.save(cargo);

        return MAPPER.toDTO(cargo);
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
        if (request.trackingPoint() != null) {
            existingCargo.setTrackingPoints(updateTrackingPoints(existingCargo, request.trackingPoint().getId()));
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
        return MAPPER.toDTO(cargoRepo.save(existingCargo));
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
        return MAPPER.toDTO(cargoRepo.save(existingCargo));
    }

    @Override
    public CargoResponseDto updateCargoCurrentLocation(Long id, Long pointId) {
        Cargo existingCargo = cargoRepo.findById(id)
                .orElseThrow(() -> new CargoNotFoundException(String.format(CARGO_N_NOT_FOUND, id)));
        existingCargo.setTrackingPoints(updateTrackingPoints(existingCargo, pointId));
        return MAPPER.toDTO(cargoRepo.save(existingCargo));
    }

    private List<TrackingPoint> updateTrackingPoints(Cargo existingCargo, Long pointId) {
        TrackingPoint trackingPoint = trackingPointRepo.findById(pointId)
            .orElseThrow(() -> new TrackingPointNotFoundException(String.format(TRACKING_POINT_N_NOT_FOUND, pointId)));
        List<TrackingPoint> trackingPoints = existingCargo.getTrackingPoints();
        trackingPoints.add(trackingPoint);
        return trackingPoints;
    }

    @Override
    public CargoResponseDto getCargo(Long id) {
        Cargo existingCargo = cargoRepo.findById(id)
                .orElseThrow(() -> new CargoNotFoundException(String.format(CARGO_N_NOT_FOUND, id)));
        return MAPPER.toDTO(existingCargo);
    }

    @Override
    public List<CargoResponseDto> getAllCargo() {
        List<Cargo> existingAllCargo = cargoRepo.findAll();
        if (existingAllCargo.isEmpty()) {
            return Collections.emptyList();
        }
        return existingAllCargo.stream().map(MAPPER::toDTO).toList();
    }

    @Override
    public void deleteCargo(Long id) {
        Cargo existingCargo = cargoRepo.findById(id)
                .orElseThrow(() -> new CargoNotFoundException(String.format(CARGO_N_NOT_FOUND, id)));
        cargoRepo.delete(existingCargo);
    }
}
