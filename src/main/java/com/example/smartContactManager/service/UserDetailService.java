package com.example.smartContactManager.service;

import com.example.smartContactManager.config.security.JwtUtils;
import com.example.smartContactManager.repository.ContactRepository;
import com.example.smartContactManager.repository.PrimaryUserlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService {

    @Autowired
    private PrimaryUserlRepository primaryUserlRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JwtUtils jwtUtils;

//    public Customer getCustomer(Principal principal) {
//        Long userId = Long.parseLong(principal.getName());
//        return customerRepository.findById(userId)
//                .orElseThrow(() -> new BadCredentialsException("User not found"));
//    }
//
//    public Customer retrieve(String token) {
//        Long id = Long.parseLong(jwtUtils.verify(token).getSubject());
//        return customerRepository.findById(id)
//                .orElseThrow(() -> new BadCredentialsException("Invalid username or password."));
//    }
}