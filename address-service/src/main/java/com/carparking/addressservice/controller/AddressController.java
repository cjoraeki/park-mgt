package com.carparking.addressservice.controller;


import com.carparking.addressservice.dto.request.AddressRequestDto;
import com.carparking.addressservice.dto.response.AddressResponseDto;
import com.carparking.addressservice.service.impl.AddressServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressServiceImpl addressServiceImpl;

    public AddressController(AddressServiceImpl addressServiceImpl) {
        this.addressServiceImpl = addressServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity<AddressResponseDto> addCustomerAddress(@Valid @RequestBody AddressRequestDto addressRequestDto){
        AddressResponseDto addressResponseDto = addressServiceImpl.createAddress(addressRequestDto);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> findCustomerAddressById(@PathVariable String id) {
        AddressResponseDto addressResponseDto = addressServiceImpl.findAddressByCustomerId(id);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressResponseDto>> findAllAddresses(){
        return new ResponseEntity<>(addressServiceImpl.findAllAddress(), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<AddressResponseDto> updateCustomerAddress(@RequestBody AddressRequestDto addressRequestDto){
        AddressResponseDto addressResponseDto = addressServiceImpl.updateAddress(addressRequestDto);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomerAddress(@PathVariable Long id){
        return new ResponseEntity<>(addressServiceImpl.deleteAddress(id), HttpStatus.OK);
    }

}
