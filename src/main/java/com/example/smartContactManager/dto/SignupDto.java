package com.example.smartContactManager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupDto {

    @NotBlank(message = "Name should not be null")
    private String name;


    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotBlank(message = "Email should not be null")
    private String email;

    @NotBlank(message = "Password should not be null")
    private String password;

    @NotBlank(message = "Confirm Password should not be null")
    private String confirmPassword;


    public String getEmail() {
        return email.toLowerCase();
    }
}
