package com.carparking.customerservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CustomerRequestDto {
    private String id;

    @NotBlank(message = "First name cannot be blank")
    private String firstname;

    @NotBlank(message = "Lastname cannot be blank")
    private String lastname;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be blank")
    private String email;
}
