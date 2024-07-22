package com.carparking.carservice.carservice;

import com.carparking.carservice.dto.request.CarRequest;
import com.carparking.carservice.dto.response.CarResponse;

import java.util.List;

public interface CarService {
    CarResponse addCar(CarRequest carRequest);
    CarResponse updateCarDetails(CarRequest carRequest);
    List<CarResponse> getCustomerCars(String customerId);
    String deleteCar(CarRequest carRequest);
    List<CarResponse> getAllCars();
}
