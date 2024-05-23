package com.example.smartContactManager.util;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("ACTIVE"),
    DELETED("DELETED"),
    PENDING("PENDING");

    private final String value;

    Status(String value) {
        this.value = value;
    }


}
