package com.carparking.addressservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {
    private Long id;

    private String lane;

    private String city;

    private String state;

    private String customerId;
}
