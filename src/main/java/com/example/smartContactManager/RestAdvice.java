package com.example.smartContactManager;


import com.example.smartContactManager.dto.CommonError;
import com.example.smartContactManager.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestControllerAdvice
@CrossOrigin(origins = "*")
public class RestAdvice {

    private List<String> fromBindingErrors(Errors errors) {
        List<String> validErrors = new ArrayList<String>();
        for (ObjectError objectError : errors.getAllErrors()) {
            validErrors.add(objectError.getDefaultMessage());
        }
        return validErrors;
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
}
