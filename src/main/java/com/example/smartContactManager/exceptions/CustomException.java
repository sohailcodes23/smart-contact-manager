package com.example.smartContactManager.exceptions;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomException extends RuntimeException {

    List<String> errors = new ArrayList<>();

    public CustomException(String exception) {
        super(exception);
        this.errors.add(exception);
    }
}
