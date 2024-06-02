package com.example.smartContactManager.service;

import com.example.smartContactManager.config.security.JwtUtils;
import com.example.smartContactManager.entity.PrimaryUser;
import com.example.smartContactManager.exceptions.ResourceNotFoundException;
import com.example.smartContactManager.repository.ContactRepository;
import com.example.smartContactManager.repository.PrimaryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class PrimaryUserService {

    @Autowired
    private PrimaryUserRepository primaryUserRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public PrimaryUser findPrimaryUserByPrincipal(Principal principal) {
        long userId = Long.parseLong(principal.getName());

        Optional<PrimaryUser> primaryUser = primaryUserRepository.findById(userId);
        if (primaryUser.isEmpty()) {
            throw new ResourceNotFoundException("User");
        }
        System.out.println("USER "+primaryUser.get().getId());
        return primaryUser.get();
    }
}