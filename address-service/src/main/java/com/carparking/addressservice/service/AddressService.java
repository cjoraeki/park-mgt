package com.carparking.addressservice.service;

import com.carparking.addressservice.dto.request.AddressRequestDto;
import com.carparking.addressservice.dto.response.AddressResponseDto;
import com.carparking.addressservice.entity.Address;

import java.util.List;

public interface AddressService {
    AddressResponseDto createAddress(AddressRequestDto addressRequestDto);
    AddressResponseDto findAddressByCustomerId(String id);
    AddressResponseDto updateAddress(AddressRequestDto addressRequestDto);
    String deleteAddress(Long id);
    List<AddressResponseDto> findAllAddress();
}
