package com.carparking.customerservice.exception;

import com.carparking.customerservice.dto.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<String> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException){
        logger.info(customerNotFoundException.getMessage());
        return new ApiResponse<>("Error: " + customerNotFoundException.getMessage(), false, null);
    }

    @ExceptionHandler(CustomerExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse<String> handleCustomerExistsException(CustomerExistsException customerExistsException){
        logger.info(customerExistsException.getMessage());
        return new ApiResponse<>("Error! " + customerExistsException.getMessage(), false, null);
    }

    @ExceptionHandler(CustomerAddressException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<String> handleCustomerAddressException(CustomerAddressException customerAddressException){
        logger.info(customerAddressException.getMessage());
        return new ApiResponse<>("Error: " + customerAddressException.getMessage(), false, null);
    }

}
