package com.programme.ProgramMe.model;

public class AuthenticationResponse {
    private final String jwt;
    private final String userType;
    private final String email;
    private final Long userId;
    private final String userName;

    public AuthenticationResponse(String jwt, String userType, String email, Long userId, String userName) {
        this.jwt = jwt;
        this.userType = userType;
        this.email = email;
        this.userId = userId;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }
}

