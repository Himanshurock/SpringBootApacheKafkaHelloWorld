package com.javainuse.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest {
    private String id;

    @JsonCreator
    public MessageRequest(@JsonProperty("id") String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
