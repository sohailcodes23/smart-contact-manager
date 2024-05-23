package com.example.smartContactManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CommonError {

    private List<String> errors;
}
