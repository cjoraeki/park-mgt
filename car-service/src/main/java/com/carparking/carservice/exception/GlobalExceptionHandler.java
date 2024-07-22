package com.carparking.carservice.exception;

import com.carparking.carservice.dto.response.ApiResponse;
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

    @ExceptionHandler(CarExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse<String> handleCarExistsException(CarExistsException carExistsException){
        logger.info(carExistsException.getMessage());
        return new ApiResponse<>(carExistsException.getMessage(),false , null);
    }

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<String> handleCarNotFoundException(CarNotFoundException carNotFoundException){
        logger.info(carNotFoundException.getMessage());
        return new ApiResponse<>(carNotFoundException.getMessage(),false , null);
    }

}
