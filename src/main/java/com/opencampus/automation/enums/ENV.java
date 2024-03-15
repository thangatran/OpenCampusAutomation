package com.opencampus.automation.enums;

import lombok.Getter;

@Getter
public enum ENV {
    PROD("prod");

    private final String name;

    ENV(String name) {
        this.name = name;
    }
}
