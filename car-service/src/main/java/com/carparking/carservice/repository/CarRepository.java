package com.carparking.carservice.repository;

import com.carparking.carservice.dto.request.CarRequest;
import com.carparking.carservice.entity.Car;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllById(Long id);
    Car findCarByPlateNumber(String plateNumber);
}
