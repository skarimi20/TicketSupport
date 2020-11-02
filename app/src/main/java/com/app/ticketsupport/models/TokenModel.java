package com.app.ticketsupport.models;

public class TokenModel {

    public TokenModel(String token) {
        Token = token;
    }

    private String Token;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
