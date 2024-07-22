package com.carparking.customerservice.service.impl;

import com.carparking.customerservice.dto.request.CustomerRequestDto;
import com.carparking.customerservice.dto.response.AddressResponseDto;
import com.carparking.customerservice.dto.response.CustomerResponseDto;
import com.carparking.customerservice.entity.Customer;
import com.carparking.customerservice.exception.CustomerAddressException;
import com.carparking.customerservice.exception.CustomerExistsException;
import com.carparking.customerservice.exception.CustomerNotFoundException;
import com.carparking.customerservice.repository.CustomerRepository;
import com.carparking.customerservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final WebClient webClient;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(WebClient webClient, ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.webClient = webClient;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerResponseDto signUp(CustomerRequestDto customerRequestDto) {
        Customer existingCustomer = customerRepository.findCustomerByEmail(customerRequestDto.getEmail());

        if (existingCustomer != null){
            throw new CustomerExistsException("Customer exists. Email: " + customerRequestDto.getEmail());
        }

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();

        Customer customer = modelMapper.map(customerRequestDto, Customer.class);
        customer.setCreatedAt(LocalDate.now());
        customer.setUpdatedAt(LocalDate.now());

        BeanUtils.copyProperties(customer, customerResponseDto);
        customerRepository.save(customer);

        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto updateCustomerDetails(CustomerRequestDto customerRequestDto) {
        Customer existingCustomer = customerRepository.findCustomerByEmail(customerRequestDto.getEmail());

        if (!Objects.equals(existingCustomer.getFirstname(), customerRequestDto.getFirstname())
                || !Objects.equals(existingCustomer.getLastname(), customerRequestDto.getLastname())
                || !Objects.equals(existingCustomer.getPhoneNumber(), customerRequestDto.getPhoneNumber())) {

            existingCustomer.setFirstname(customerRequestDto.getFirstname());
            existingCustomer.setLastname(customerRequestDto.getLastname());
            existingCustomer.setPhoneNumber(customerRequestDto.getPhoneNumber());
            existingCustomer.setUpdatedAt(LocalDate.now());

            return modelMapper.map(customerRepository.save(existingCustomer), CustomerResponseDto.class);
        } else {
            return modelMapper.map(existingCustomer, CustomerResponseDto.class);
        }
    }

    @Override
    public String deleteCustomer(String id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found. ID: " + id));

        customerRepository.delete(existingCustomer);
        return "Customer successfully deleted";
    }

    @Override
    public Optional<CustomerResponseDto> findCustomerAddressById(String id) {
        AddressResponseDto addressResponse = new AddressResponseDto();
        try {
             addressResponse = webClient.get().uri("/"+ id)
                    .retrieve()
                    .bodyToMono(AddressResponseDto.class)
                    .block();
        }catch (WebClientResponseException e){
            if (e.getStatusCode() == HttpStatusCode.valueOf(404)){
                throw new CustomerAddressException("Customer address does not exist");
            }
        }

        CustomerResponseDto customerResponseDto = (customerRepository.findCustomerById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found. ID: " + id)));
        customerResponseDto.setAddressResponseDto(addressResponse);

        return Optional.of(customerResponseDto);
    }

    @Override
    public Optional<CustomerResponseDto> findCustomerById(String id) {
        Optional<CustomerResponseDto> customerResponseDto = customerRepository.findCustomerById(id);
        if (customerResponseDto.isEmpty()){
            throw new CustomerNotFoundException("Customer not found");
        }

        return customerResponseDto;
    }


    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()){
            throw new CustomerNotFoundException( "No registered customers");
        }
        return customerList;
    }
}
