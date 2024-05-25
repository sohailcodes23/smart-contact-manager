package com.example.smartContactManager.exceptions;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlreadyExistsException extends RuntimeException {

    List<String> errors = new ArrayList<>();

    public AlreadyExistsException(String exception) {
        super(exception + " already exists");
        this.errors.add(exception + " already exists");
    }
}
