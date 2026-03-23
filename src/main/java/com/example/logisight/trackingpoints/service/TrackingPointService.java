package com.example.logisight.trackingpoints.service;

import com.example.logisight.trackingpoints.dto.TrackingPointDto;

import java.util.List;

public interface TrackingPointService {
    String TRACKING_POINT_N_NOT_FOUND = "Контрольная точка с ID %d не найдена.";
    TrackingPointDto createPoint(TrackingPointDto dto);

    TrackingPointDto getPointById(Long id);

    List<TrackingPointDto> getAllPoints();

    TrackingPointDto updatePoint(Long id, TrackingPointDto dto);

    void deletePoint(Long id);
}