package com.example.smartContactManager.controller;

import com.example.smartContactManager.dto.AuthResponse;
import com.example.smartContactManager.dto.CommonMessage;
import com.example.smartContactManager.dto.LoginDto;
import com.example.smartContactManager.dto.SignupDto;
import com.example.smartContactManager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("login")
    private ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("sign-up")
    private ResponseEntity<CommonMessage> signUp(@RequestBody @Valid SignupDto loginDto) {
        return ResponseEntity.ok(authService.signUp(loginDto));
    }

}
