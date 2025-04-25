package com.example.logisight.cargo.repo;

import com.example.logisight.cargo.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepo extends JpaRepository<Cargo, Long> {

    boolean existsByTrackingNumberIgnoreCase(String trackingNumber);
}
