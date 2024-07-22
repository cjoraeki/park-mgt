package com.carparking.addressservice.okb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class UserMapping {

    public static void main(String[] args) throws JsonProcessingException {
//        String jsonResponse2 = "{\" id\":\"M:1714732823:2347062126363:723:eg\", \"sessionId\": \"S:1714732806:2347062126363:723:dix\", \"msisdn\":\"2347062126363\",\"imsi\": \"5747084814\", \"starcode\":\"723\",\"query\":\"*723*2020#\", \"input\": \"1111\", \"sessionData\": {\"baseUrl\":\"https://biya-ussd-3cd0703b6164.herokuapp.com/api/v1\", \"phoneNumber\": \"2347062126363\", \"fullName\":\"Amanda Chi\", \"transactionPin\": \"1111\"}, \"sessionStart\": false, \"data\":{}}";
        String jsonResponse = "{\"id\":\"M:1714732823:2347062126363:723:eg\",\"sessionId\":\"S:1714732806:2347062126363:723:dix\",\"msisdn\":\"2347062126363\",\"imsi\":\"5747084814\",\"starcode\":\"723\",\"query\":\"*723*2020#\",\"input\":\"1111\",\"sessionData\":{\"baseUrl\":\"https://biya-ussd-3cd0703b6164.herokuapp.com/api/v1\",\"phoneNumber\":\"2347062126363\",\"fullName\":\"Amanda Chi\",\"transactionPin\":\"1111\"},\"sessionStart\":false,\"data\":{}}";

        try {
            // Parse JSON string into a map
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(jsonResponse, Map.class);


            String fullName = (String) ((Map) map.get("sessionData")).get("fullName");
            String phoneNumber = (String) ((Map) map.get("sessionData")).get("phoneNumber");
            String transactionPin = (String) ((Map) map.get("sessionData")).get("transactionPin");

            SessionDataDto sessionDataDto = new SessionDataDto();
            sessionDataDto.setFullName(fullName);
            sessionDataDto.setPhoneNumber(phoneNumber);
            sessionDataDto.setTransactionPin(transactionPin);

            System.out.println("Full Name: " + fullName);
            System.out.println("Phone Number: " + phoneNumber);
            System.out.println("Transaction Pin: " + transactionPin);

            System.out.println(sessionDataDto);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        ObjectMapper objectMapper = new ObjectMapper();
//
//
//        Map map = objectMapper.readValue(jsonResponse, Map.class);
//        System.out.println("Response: "+map);
//
//        UserRequestDto userRequestDto = new UserRequestDto();
//
//        userRequestDto.setFullName((String) map.get("fullName"));
//        userRequestDto.setPhoneNumber((String) map.get("phoneNumber"));
//        userRequestDto.setTransactionPin((String) map.get("transactionPin"));
//
//        System.out.println("Registration details:");
//        System.out.println("Full Name: " + userRequestDto.getFullName());
//        System.out.println("Phone Number: " + userRequestDto.getPhoneNumber());
//        System.out.println("Transaction Pin: " + userRequestDto.getTransactionPin());
//
//        System.out.println(userRequestDto.getData());

    }
}
