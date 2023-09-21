package com.example.demospring.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class JwtResponse {
    @JsonProperty("accessToken")
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getJwtToken() {
        return token;
    }

}
