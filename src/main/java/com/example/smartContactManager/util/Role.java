package com.example.smartContactManager.util;

import lombok.Getter;

@Getter
public enum Role {
    PRIMARY_USER("PRIMARY_USER"),
    ADMIN("ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }


}
