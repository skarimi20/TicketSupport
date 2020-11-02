package com.app.ticketsupport.models;

public class SaveUser {

    public SaveUser(String name, String number, String password) {
        this.name = name;
        this.number = number;
        this.password = password;
    }

    private String name;
    private String number;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
