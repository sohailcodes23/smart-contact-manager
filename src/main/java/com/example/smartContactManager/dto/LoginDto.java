package com.example.smartContactManager.dto;

import lombok.Data;

@Data
public class LoginDto {

    private String email;
    private String password;

    public String getEmail() {
        return email.toLowerCase();
    }
}
