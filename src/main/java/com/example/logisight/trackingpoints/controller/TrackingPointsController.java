package com.example.logisight.trackingpoints.controller;

import com.example.logisight.trackingpoints.dto.TrackingPointDto;
import com.example.logisight.trackingpoints.service.TrackingPointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tracking-points")
@RequiredArgsConstructor
public class TrackingPointsController {

    private final TrackingPointService trackingPointService;

    @PostMapping
    public TrackingPointDto create(@RequestBody @Valid TrackingPointDto point) {
        return trackingPointService.createPoint(point);
    }

    @GetMapping("/{id}")
    public TrackingPointDto getById(@PathVariable Long id) {
        return trackingPointService.getPointById(id);
    }

    @GetMapping
    public List<TrackingPointDto> getAll() {
        return trackingPointService.getAllPoints();
    }

    @PutMapping("/{id}")
    public TrackingPointDto update(@PathVariable Long id, @RequestBody @Valid TrackingPointDto dto) {
        return trackingPointService.updatePoint(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trackingPointService.deletePoint(id);
    }
}
