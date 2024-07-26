package com.example.carservice.repository;

import com.example.carservice.model.Customer;
import com.example.carservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
