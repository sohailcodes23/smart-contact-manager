package com.example.smartContactManager.controller;

import com.example.smartContactManager.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()

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
