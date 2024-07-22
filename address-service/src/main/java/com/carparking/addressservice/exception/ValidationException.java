package com.carparking.addressservice.exception;


public class ValidationException extends RuntimeException{

    public ValidationException(){
        super("Validation failed");
    }

    public ValidationException(String message){
        super(message);
    }
}
