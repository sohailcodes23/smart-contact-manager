package com.example.smartContactManager.controller;

import com.example.smartContactManager.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController()
@Scope(value = "request")
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserDetailService userDetailService;

//    @GetMapping
//    private ResponseEntity test(Principal principal) {
//        System.out.println("ID " + principal.getName());
//        return ResponseEntity.ok(userDetailService.getCustomer(principal));
//    }

}
