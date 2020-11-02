package com.app.ticketsupport.serverConnection;

public interface IResponse<T> {

    public void onSuccess(T response);
    public void onFailuer(String errorMessage);
}
