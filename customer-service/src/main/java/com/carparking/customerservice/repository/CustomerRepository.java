package com.carparking.customerservice.repository;

import com.carparking.customerservice.dto.response.CustomerResponseDto;
import com.carparking.customerservice.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<CustomerResponseDto> findCustomerById(String id);

    Customer findCustomerByEmail(String email);

}
