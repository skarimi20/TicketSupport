package com.app.ticketsupport.ui.login;

import com.app.ticketsupport.models.LoginResponse;

public interface ILogin<T> {
    public void onSuccess(T response);
    public void onFailure(String Erorr);
}
