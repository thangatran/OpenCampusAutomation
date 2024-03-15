package com.opencampus.automation.bases;

import com.google.gson.GsonBuilder;

public class BaseDto {
    public String toJsonString() {
        return new GsonBuilder().setLenient().create().toJson(this);
    }
}
