package com.carparking.carservice.controller;

import com.carparking.carservice.carservice.impl.CarServiceImpl;
import com.carparking.carservice.dto.request.CarRequest;
import com.carparking.carservice.dto.response.CarResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarServiceImpl carService;

    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @PostMapping("/add")
    public ResponseEntity<CarResponse> addCarToCustomer(@Valid @RequestBody CarRequest carRequest, String plateNumber){
        return new ResponseEntity<>(carService.addCar(carRequest), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<CarResponse> updateCustomerCar(@RequestBody CarRequest carRequest){
        return new ResponseEntity<>(carService.updateCarDetails(carRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CarResponse>> getCustomerCar(@PathVariable String id){
        return new ResponseEntity<>(carService.getCustomerCars(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomerCar(@RequestBody CarRequest carRequest){
        return new ResponseEntity<>(carService.deleteCar(carRequest), HttpStatus.OK);
    }

    @GetMapping("/all-cars")
    public ResponseEntity<List<CarResponse>> addCarToCustomer(){
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }
}
