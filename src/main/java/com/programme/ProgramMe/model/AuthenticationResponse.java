package com.programme.ProgramMe.model;

public class AuthenticationResponse {
    private final String jwt;
    private final String userType;

    public AuthenticationResponse(String jwt, String userType) {
        this.jwt = jwt;
        this.userType = userType;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUserType() {
        return userType;
    }
}
