package com.example.logisight.vehicles.repo;

import com.example.logisight.vehicles.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
}
