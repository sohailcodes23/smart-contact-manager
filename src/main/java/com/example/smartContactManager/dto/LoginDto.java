package com.example.smartContactManager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDto {
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid Email")
    @NotBlank(message = "Email should not be null")
    private String email;

    @NotBlank(message = "Password should not be null")
    private String password;

    public String getEmail() {
        return email.toLowerCase();
    }
}
