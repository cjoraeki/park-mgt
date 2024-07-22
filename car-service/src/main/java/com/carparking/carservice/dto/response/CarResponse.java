package com.carparking.carservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarResponse {

    private String brand;

    private String plateNumber;

    private String color;

    private String bodyType; // such as sedan, SUV, coupe, hatchback

    private Integer yearOfManufacture;

    private String customerId;

    private CustomerResponse customerResponse;
}
