package com.example.logisight.trackingpoints.repo;

import com.example.logisight.trackingpoints.model.TrackingPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingPointRepository extends JpaRepository<TrackingPoint, Long> {
}
