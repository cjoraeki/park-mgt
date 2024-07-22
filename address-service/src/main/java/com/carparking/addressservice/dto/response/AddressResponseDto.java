package com.carparking.addressservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponseDto {

    private String lane;

    private String city;

    private String state;

    private String customerId;
}
