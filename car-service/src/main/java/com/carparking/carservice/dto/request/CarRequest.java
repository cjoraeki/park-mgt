package com.carparking.carservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequest {
    private Long id;

    private String brand;

    @NotBlank(message = "Plate number cannot be empty")
    private String plateNumber;

    private String color;

    private String bodyType; // such as sedan, SUV, coupe, hatchback

    private Integer yearOfManufacture;

    private String customerId;
}
