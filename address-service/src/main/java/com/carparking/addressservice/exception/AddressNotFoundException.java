package com.carparking.addressservice.exception;

public class AddressNotFoundException extends RuntimeException{

    public AddressNotFoundException(){
        super("Address not found");
    }

    public AddressNotFoundException(String message){
        super(message);
    }
}
