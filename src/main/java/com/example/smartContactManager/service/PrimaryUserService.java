package com.example.smartContactManager.service;

import com.example.smartContactManager.config.security.JwtAuthenticationToken;
import com.example.smartContactManager.config.security.JwtUtils;
import com.example.smartContactManager.entity.PrimaryUser;
import com.example.smartContactManager.exceptions.ResourceNotFoundException;
import com.example.smartContactManager.repository.ContactRepository;
import com.example.smartContactManager.repository.PrimaryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return primaryUser.get();
    }

    public PrimaryUser findById(Long userId) {
        return primaryUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User"));
    }

    public PrimaryUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
            Long userId = jwtAuthenticationToken.getPrincipal() != null ? (Long) jwtAuthenticationToken.getPrincipal() : null;
            System.out.println("CURRENT USERID " + userId);
            return findById(userId);
        }
        return null;
    }
}