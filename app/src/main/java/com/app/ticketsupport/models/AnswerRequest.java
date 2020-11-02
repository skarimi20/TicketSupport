package com.app.ticketsupport.models;

public class AnswerRequest {
    private String id;

    public AnswerRequest(String id, String message) {
        this.id = id;
        this.message = message;
    }

    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
