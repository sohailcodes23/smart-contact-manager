package com.example.smartContactManager.dto;

import lombok.Data;

@Data
public class SignupDto {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;


    public String getEmail() {
        return email.toLowerCase();
    }
}
