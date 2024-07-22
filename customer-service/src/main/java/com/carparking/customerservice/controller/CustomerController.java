package com.carparking.customerservice.controller;

import com.carparking.customerservice.dto.request.CustomerRequestDto;
import com.carparking.customerservice.dto.response.CustomerResponseDto;
import com.carparking.customerservice.entity.Customer;
import com.carparking.customerservice.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @PostMapping("/signup")
    public ResponseEntity<CustomerResponseDto> signUpToPark(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto customerResponseDto = customerServiceImpl.signUp(customerRequestDto);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return new ResponseEntity<>(customerServiceImpl.updateCustomerDetails(customerRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        return new ResponseEntity<>(customerServiceImpl.deleteCustomer(id), HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Optional<CustomerResponseDto>> getCustomerAddressById(@Valid @PathVariable String id) {
        return new ResponseEntity<>(customerServiceImpl.findCustomerAddressById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CustomerResponseDto>> getCustomerById(@Valid @PathVariable String id) {
        return new ResponseEntity<>(customerServiceImpl.findCustomerById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<>(customerServiceImpl.findAllCustomers(), HttpStatus.OK);
    }
}
