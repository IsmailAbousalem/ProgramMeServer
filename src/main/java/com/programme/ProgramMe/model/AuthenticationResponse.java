package com.programme.ProgramMe.model;

public class AuthenticationResponse {
    private final String jwt;
    private final String userType;
    private final String email;
    private final Long userId;

    public AuthenticationResponse(String jwt, String userType, String email, Long userId) {
        this.jwt = jwt;
        this.userType = userType;
        this.email = email;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public Long getUserId() {
        return userId;
    }
}

