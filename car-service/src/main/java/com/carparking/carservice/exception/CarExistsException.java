package com.carparking.carservice.exception;

public class CarExistsException extends RuntimeException{
    public CarExistsException(){
        super("Car already exists");
    }

    public CarExistsException(String message){
        super(message);
    }
}
