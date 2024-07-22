package com.carparking.customerservice.exception;


public class CustomerExistsException extends RuntimeException{

    public CustomerExistsException(){
        super("Customer already exists");
    }

    public CustomerExistsException(String message) {
        super(message);
    }

}
