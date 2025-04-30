package com.example.logisight.trackingpoints.service;

import com.example.logisight.trackingpoints.dto.TrackingPointDto;
import com.example.logisight.trackingpoints.exception.TrackingPointNotFoundException;
import com.example.logisight.trackingpoints.mapper.TrackingPointMapper;
import com.example.logisight.trackingpoints.model.TrackingPoint;
import com.example.logisight.trackingpoints.repo.TrackingPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingPointServiceImpl implements TrackingPointService {
    private static final TrackingPointMapper MAPPER = TrackingPointMapper.INSTANCE;
    private final TrackingPointRepository repository;

    @Override
    public TrackingPointDto createPoint(TrackingPointDto dto) {
        TrackingPoint trackingPoint = MAPPER.toEntity(dto);
        return MAPPER.toDto(repository.save(trackingPoint));
    }

    @Override
    public TrackingPointDto getPointById(Long id) {
        TrackingPoint existTrackingPoint = repository.findById(id)
                .orElseThrow(() -> new TrackingPointNotFoundException(String.format(TRACKING_POINT_N_NOT_FOUND, id)));
        return MAPPER.toDto(existTrackingPoint);
    }

    @Override
    public List<TrackingPointDto> getAllPoints() {
        List<TrackingPoint> trackingPoints = repository.findAll();
        if (trackingPoints.isEmpty())
            return Collections.emptyList();
        return trackingPoints.stream().map(MAPPER::toDto).toList();
    }

    @Override
    public TrackingPointDto updatePoint(Long id, TrackingPointDto dto) {
        TrackingPoint trackingPoint = repository.findById(id)
                .orElseThrow(() -> new TrackingPointNotFoundException(String.format(TRACKING_POINT_N_NOT_FOUND, id)));

        if (dto.getName() != null) {
            trackingPoint.setName(dto.getName());
        }
        if (dto.getLatitude() != null) {
            trackingPoint.setLatitude(dto.getLatitude());
        }
        if (dto.getLongitude() != null) {
            trackingPoint.setLongitude(dto.getLongitude());
        }

        trackingPoint.setActive(dto.isActive());

        return MAPPER.toDto(repository.save(trackingPoint));
    }

    @Override
    public void deletePoint(Long id) {
        TrackingPoint trackingPoint = repository.findById(id)
                .orElseThrow(() -> new TrackingPointNotFoundException(String.format(TRACKING_POINT_N_NOT_FOUND, id)));
        repository.delete(trackingPoint);
    }
}
