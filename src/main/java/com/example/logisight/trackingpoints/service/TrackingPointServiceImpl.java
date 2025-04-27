package com.example.logisight.trackingpoints.service;

import com.example.logisight.trackingpoints.dto.TrackingPointDto;
import com.example.logisight.trackingpoints.exception.TrackingPointNotFoundException;
import com.example.logisight.trackingpoints.model.TrackingPoint;
import com.example.logisight.trackingpoints.repo.TrackingPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingPointServiceImpl implements TrackingPointService {

    private static final String TRACKING_POINT_NOT_FOUND = "Контрольная точка с ID %d не найдена.";
    private final TrackingPointRepository repository;

    @Override
    public TrackingPointDto createPoint(TrackingPointDto dto) {
        return TrackingPointDto.fromEntity(repository.save(dto.toEntity()));
    }

    @Override
    public TrackingPointDto getPointById(Long id) {
        TrackingPoint existTrackingPoint = repository.findById(id)
                .orElseThrow(() -> new TrackingPointNotFoundException(String.format(TRACKING_POINT_NOT_FOUND, id)));
        return TrackingPointDto.fromEntity(existTrackingPoint);
    }

    @Override
    public List<TrackingPointDto> getAllPoints() {
        List<TrackingPoint> trackingPoints = repository.findAll();
        if (trackingPoints.isEmpty())
            return Collections.emptyList();
        return trackingPoints.stream().map(TrackingPointDto::fromEntity).toList();
    }

    @Override
    public TrackingPointDto updatePoint(Long id, TrackingPointDto dto) {
        TrackingPoint trackingPoint = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(TRACKING_POINT_NOT_FOUND));

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

        return TrackingPointDto.fromEntity(repository.save(trackingPoint));
    }

    @Override
    public void deletePoint(Long id) {
        TrackingPoint trackingPoint = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(TRACKING_POINT_NOT_FOUND));
        repository.delete(trackingPoint);
    }
}
