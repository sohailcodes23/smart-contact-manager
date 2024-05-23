package com.example.smartContactManager.util;

import lombok.Getter;

@Getter
public enum Role {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }


}
