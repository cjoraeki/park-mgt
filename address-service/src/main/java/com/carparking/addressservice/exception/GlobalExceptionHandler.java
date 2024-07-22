package com.carparking.addressservice.exception;

import com.carparking.addressservice.dto.response.ApiResponse;
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

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<String> handleNotFoundException(AddressNotFoundException addressNotFoundException){
        logger.info(addressNotFoundException.getMessage());
        return new ApiResponse<>("Error: " + addressNotFoundException.getMessage(), false, null);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<String> handleValidationException(ValidationException validationException){
        logger.info(validationException.getMessage());
        return new ApiResponse<>("Error: " + validationException.getMessage(), false, null);
    }
}
