package com.example.smartContactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("general")
public class GeneralController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("user")
    public String getUser() {
        //Admin : $2a$10$QlJNAdLPCiZA/oevIh3CcuGsPZaz6159CsVAO7FTCphRYyuaEdmG2
        //INSERT INTO IAM_OBJECT   ( ADMIN_ID , ID, PASSWORD , ROLE , STATUS , USERNAME ,)
        //VALUES (NOW(), 1, NOW(), 'admin@admin.com');
        System.out.println("GETTING USERS " + passwordEncoder.encode("admin"));
        return "Users";
    }

    @GetMapping("current-user")
    public String getCurrentUser(Principal principal) {
        return principal.getName();
    }
}
