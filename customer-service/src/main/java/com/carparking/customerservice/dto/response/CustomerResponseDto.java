package com.carparking.customerservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private String email;

    private AddressResponseDto addressResponseDto;
}
