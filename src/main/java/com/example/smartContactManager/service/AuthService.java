package com.example.smartContactManager.service;

import com.example.smartContactManager.config.security.JwtUtils;
import com.example.smartContactManager.dto.AuthResponse;
import com.example.smartContactManager.dto.CommonMessage;
import com.example.smartContactManager.dto.LoginDto;
import com.example.smartContactManager.dto.SignupDto;
import com.example.smartContactManager.entity.PrimaryUser;
import com.example.smartContactManager.exceptions.AlreadyExistsException;
import com.example.smartContactManager.exceptions.CustomException;
import com.example.smartContactManager.exceptions.ResourceNotFoundException;
import com.example.smartContactManager.repository.ContactRepository;
import com.example.smartContactManager.repository.PrimaryUserlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PrimaryUserlRepository primaryUserlRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public CommonMessage signUp(SignupDto signupDto) {

        if (primaryUserlRepository.existsByEmail(signupDto.getEmail())) {
            throw new AlreadyExistsException("User");
        }

        if (!signupDto.getPassword().equals(signupDto.getConfirmPassword())) {
            throw new CustomException("Password should be same as confirm password");
        }

        PrimaryUser primaryUser = new PrimaryUser();
        primaryUser.setEmail(signupDto.getEmail());
        primaryUser.setName(signupDto.getName());
        primaryUser.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        primaryUserlRepository.save(primaryUser);


        return new CommonMessage("Successful");
    }

    public AuthResponse login(LoginDto loginDto) {
        PrimaryUser primaryUser = primaryUserlRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User"));

        if (!passwordEncoder.matches(loginDto.getPassword(), primaryUser.getPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        Object data = primaryUser;
        Long userId = primaryUser.getId();
        String token = jwtUtils.createToken(userId);
        return new AuthResponse(token, data);
    }

}
