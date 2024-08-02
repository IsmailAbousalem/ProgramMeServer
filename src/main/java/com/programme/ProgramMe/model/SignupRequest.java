package com.programme.ProgramMe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String email;
    private String password;
    private String name;
    private String number;
    private String userType; // "customer" or "programmer"
    private String skills; // Only for Programmer
    private String description; // Only for Programmer
}
