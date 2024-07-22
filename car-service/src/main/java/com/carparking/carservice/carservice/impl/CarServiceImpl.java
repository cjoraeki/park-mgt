package com.carparking.carservice.carservice.impl;

import com.carparking.carservice.carservice.CarService;
import com.carparking.carservice.dto.request.CarRequest;
import com.carparking.carservice.dto.response.CarResponse;
import com.carparking.carservice.dto.response.CustomerResponse;
import com.carparking.carservice.entity.Car;
import com.carparking.carservice.exception.CarExistsException;
import com.carparking.carservice.exception.CarNotFoundException;
import com.carparking.carservice.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final WebClient webClient;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, WebClient webClient) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.webClient = webClient;
    }

    @Override
    public CarResponse addCar(CarRequest carRequest) {
        Car existingCar = carRepository.findCarByPlateNumber(carRequest.getPlateNumber());
        if (existingCar != null){
            throw new CarExistsException("Car already exists");
        }
        Car car = modelMapper.map(carRequest, Car.class);
        car.setCreatedAt(LocalDate.now());
        car.setUpdatedAt(LocalDate.now());

        CarResponse carResponse = new CarResponse();
        BeanUtils.copyProperties(car, carResponse);

        carRepository.save(car);

        return carResponse;
    }

    @Override
    public CarResponse updateCarDetails(CarRequest carRequest) {
        Car existingCar = carRepository.findById(carRequest.getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        existingCar.setBrand(carRequest.getBrand());
        existingCar.setColor(carRequest.getColor());
        existingCar.setBodyType(carRequest.getBodyType());
        existingCar.setPlateNumber(carRequest.getPlateNumber());
        existingCar.setYearOfManufacture(carRequest.getYearOfManufacture());
        existingCar.setUpdatedAt(LocalDate.now());

        CarResponse carResponse = new CarResponse();
        BeanUtils.copyProperties(existingCar, carResponse);
        carRepository.save(existingCar);

        return carResponse;
    }

    @Override
    public List<CarResponse> getCustomerCars(String customerId) {
//        CustomerResponse customerResponse = new CustomerResponse();
//        try {
//            customerResponse = webClient.get().uri("/"+customerId).retrieve().bodyToMono(CustomerResponse.class).block();
//
//        }catch (WebClientResponseException ex) {
//            if (ex.getStatusCode() == HttpStatusCode.valueOf(404)){
//                throw new CarNotFoundException("Customer cars does not exist");
//            }        }

        List<Car> existingCars = carRepository.findAllById(Long.valueOf(customerId));

        List<CarResponse> carResponseList = new ArrayList<>();
        for (Car car : existingCars){
            CarResponse carResponse = modelMapper.map(car, CarResponse.class);
//            carResponse.setCustomerResponse(customerResponse);
            carResponseList.add(carResponse);
        }

        return carResponseList;
    }

    @Override
    public String deleteCar(CarRequest carRequest) {
        Car existingCar = carRepository.findById(carRequest.getId())
                .orElseThrow(()-> new RuntimeException("Car does not exist. ID: " + carRequest.getId()));

        carRepository.delete(existingCar);

        return "Car deleted successfully. ID: "+ carRequest.getId();
    }

    @Override
    public List<CarResponse> getAllCars() {
        List<Car> existingCars = carRepository.findAll();
        if (existingCars.isEmpty()){
            throw new CarNotFoundException("Cars not found");
        }
        List<CarResponse> carResponseList = new ArrayList<>();

        for (Car car : existingCars){
            CarResponse carResponse = modelMapper.map(car, CarResponse.class);
            carResponseList.add(carResponse);
        }
        return carResponseList;
    }

}
