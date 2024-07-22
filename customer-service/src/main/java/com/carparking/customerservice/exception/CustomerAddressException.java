package com.carparking.customerservice.exception;

public class CustomerAddressException extends RuntimeException{
    public CustomerAddressException(){
        super("Customer address has not been created");
    }

    public CustomerAddressException(String message){
        super(message);
    }
}
