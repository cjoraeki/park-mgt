package com.carparking.carservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerResponse {
    private String firstname;

    private String lastname;

    private String phoneNumber;

    private String email;
}
