package com.example.smartContactManager.controller;

import com.example.smartContactManager.dto.AuthResponse;
import com.example.smartContactManager.dto.LoginDto;
import com.example.smartContactManager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Scope(value = "request")
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("login")
    private ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

}
