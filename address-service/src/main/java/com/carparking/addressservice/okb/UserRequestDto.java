package com.carparking.addressservice.okb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @JsonIgnore
    private String id;

    @JsonIgnore
    private String sessionId;

    @JsonIgnore
    private String msisdn;

    @JsonIgnore
    private String imsi;

    @JsonIgnore
    private String starcode;

    @JsonIgnore
    private String query;

    @JsonIgnore
    private String input;

    private SessionDataDto sessionData;

    @JsonIgnore
    private boolean sessionStart;

    @JsonIgnore
    private Object data;

}
