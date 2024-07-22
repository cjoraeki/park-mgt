package com.carparking.customerservice.service;

import com.carparking.customerservice.dto.request.CustomerRequestDto;
import com.carparking.customerservice.dto.response.CustomerResponseDto;
import com.carparking.customerservice.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerResponseDto signUp(CustomerRequestDto customerRequestDto);
    CustomerResponseDto updateCustomerDetails(CustomerRequestDto customerRequestDto);
    String deleteCustomer(String id);
    Optional<CustomerResponseDto> findCustomerAddressById(String id);
    Optional<CustomerResponseDto> findCustomerById(String id);
    List<Customer> findAllCustomers();
}
