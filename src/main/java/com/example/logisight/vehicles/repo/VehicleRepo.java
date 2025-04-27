package com.example.logisight.vehicles.repo;

import com.example.logisight.vehicles.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByIsAvailableTrue();

    List<Vehicle> findByVehicleTypeIgnoreCase(String vehicleType);
}
