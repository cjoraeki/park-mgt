package com.carparking.addressservice.okb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionDataDto {
    private String fullName;

    private String phoneNumber;

    private String transactionPin;

    @Override
    public String toString() {
        return "SessionDataDto {" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", transactionPin='" + transactionPin + '\'' +
                '}';
    }
}
