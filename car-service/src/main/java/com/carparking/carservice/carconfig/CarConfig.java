package com.carparking.carservice.carconfig;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CarConfig {

    @Value("${car-service.base.url}")
    private String carBaseUrl;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder().baseUrl(carBaseUrl).build();
    }
}
