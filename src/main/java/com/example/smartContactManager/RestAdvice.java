package com.example.smartContactManager;


import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.smartContactManager.dto.CommonError;
import com.example.smartContactManager.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestControllerAdvice
@CrossOrigin(origins = "*")
public class RestAdvice {


    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<CommonError> signatureVerificationException(SignatureVerificationException e) {
        return ResponseEntity.status(401).body(new CommonError(Arrays.asList("Invalid User")));
    }


    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<CommonError> tokenExpiredException(TokenExpiredException e) {
        return ResponseEntity.status(401).body(new CommonError(Arrays.asList("Invalid User")));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<CommonError> generic(Exception ex) {
        return ResponseEntity.status(500).body(new CommonError(Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonError> runtimeException(RuntimeException ex) {
        return ResponseEntity.status(500).body(new CommonError(Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CommonError> resourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(new CommonError(Collections.singletonList(ex.getMessage())));
    }


    private List<String> fromBindingErrors(Errors errors) {
        List<String> validErrors = new ArrayList<String>();
        for (ObjectError objectError : errors.getAllErrors()) {
            validErrors.add(objectError.getDefaultMessage());
        }
        return validErrors;
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonError> invalidInput(BindException e) {
        BindingResult result = e.getBindingResult();
        return ResponseEntity.status(500).body(new CommonError(fromBindingErrors(result)));
    }
}
