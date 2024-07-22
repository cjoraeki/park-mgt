package com.carparking.addressservice.service.impl;

import com.carparking.addressservice.dto.request.AddressRequestDto;
import com.carparking.addressservice.dto.response.AddressResponseDto;
import com.carparking.addressservice.entity.Address;
import com.carparking.addressservice.exception.AddressNotFoundException;
import com.carparking.addressservice.repositroy.AddressRepository;
import com.carparking.addressservice.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public AddressResponseDto createAddress(AddressRequestDto addressRequestDto) {
        Address address = modelMapper.map(addressRequestDto, Address.class);
        AddressResponseDto addressResponseDto = modelMapper.map(address, AddressResponseDto.class);

        addressRepository.save(address);
        return addressResponseDto;
    }

    @Override
    public AddressResponseDto findAddressByCustomerId(String id) {
        Address address = addressRepository.findByCustomerId(id);

        if (address == null){
            throw new AddressNotFoundException("Customer not found. ID: " + id);
        }
        AddressResponseDto addressResponseDto = new AddressResponseDto();
        BeanUtils.copyProperties(address, addressResponseDto);

        return addressResponseDto;
    }

    @Override
    public AddressResponseDto updateAddress(AddressRequestDto addressRequestDto) {
        Address existingAddress = addressRepository.findById(addressRequestDto.getId())
                .orElseThrow(() -> new AddressNotFoundException("Address does not exist"));

        existingAddress.setLane(addressRequestDto.getLane());
        existingAddress.setCity(addressRequestDto.getCity());
        existingAddress.setState(addressRequestDto.getState());

        Address updatedAddress = addressRepository.save(existingAddress);

        return modelMapper.map(updatedAddress, AddressResponseDto.class);

    }

    @Override
    public String deleteAddress(Long id) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(()-> new AddressNotFoundException("Address does not exist"));

        addressRepository.delete(existingAddress);

        return "Address successfully deleted";
    }

    @Override
    public List<AddressResponseDto> findAllAddress() {
        List<AddressResponseDto> responses = new ArrayList<>();

        List<Address> addresses= addressRepository.findAll();
        for (Address address: addresses){
            AddressResponseDto addressResponseDto = modelMapper.map(address, AddressResponseDto.class);
            responses.add(addressResponseDto);
        }
        return responses;
    }
}

