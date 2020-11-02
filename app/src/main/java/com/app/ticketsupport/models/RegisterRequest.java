package com.app.ticketsupport.models;

public class RegisterRequest {
    public RegisterRequest(String mobileNumber, String secretCode) {
        this.mobileNumber = mobileNumber;
        SecretCode = secretCode;
    }

    private String mobileNumber;
    private String SecretCode;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSecretCode() {
        return SecretCode;
    }

    public void setSecretCode(String secretCode) {
        SecretCode = secretCode;
    }
}
