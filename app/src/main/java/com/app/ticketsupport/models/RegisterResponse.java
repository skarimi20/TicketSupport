package com.app.ticketsupport.models;

public class RegisterResponse {

    public RegisterResponse(Boolean isSuccessful) {
        IsSuccessful = isSuccessful;
    }

    private Boolean IsSuccessful;

    public Boolean getSuccessful() {
        return IsSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        IsSuccessful = successful;
    }
}
