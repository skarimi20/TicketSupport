package com.app.ticketsupport.models;

public class UpdateUserModel {
    private String name;

    public UpdateUserModel(String name, String number) {
        this.name = name;
        this.number = number;
    }

    private String number;
    private String newPassword;
    private String oldPassword;

    public UpdateUserModel(String name, String number, String newPassword, String oldPassword) {
        this.name = name;
        this.number = number;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }


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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
