package com.app.ticketsupport.ui.login;

import android.content.Context;

import com.app.ticketsupport.models.LoginRequest;
import com.app.ticketsupport.models.LoginResponse;
import com.app.ticketsupport.models.RegisterRequest;
import com.app.ticketsupport.models.RegisterResponse;
import com.app.ticketsupport.models.SaveUser;
import com.app.ticketsupport.models.TokenModel;
import com.app.ticketsupport.serverConnection.IResponse;
import com.app.ticketsupport.serverConnection.TicketAPI;
import com.app.ticketsupport.serverConnection.TokenClass;

import java.util.Random;
/**
 * Get Values from View and make require changes for serverConnection.TicketAPI
 */
public class LoginRepository {
    private TicketAPI ticketAPI;
    private TokenClass tokenClass;
    private String code;

    public LoginRepository(Context context){
        ticketAPI = new TicketAPI();
        tokenClass = new TokenClass(context);
    }

    public void LoginUser (String number, String password, ILogin iLogin){
        LoginRequest loginRequest = new LoginRequest(number,password);
        ticketAPI.loginUser(loginRequest, new IResponse() {
            @Override
            public void onSuccess(Object response) {
                LoginResponse loginResponse = (LoginResponse) response;
                iLogin.onSuccess(response);
                tokenClass.saveToken(loginResponse.getToken(),loginResponse.getName());
            }

            @Override
            public void onFailuer(String errorMessage) {
                iLogin.onFailure(errorMessage);

            }
        });

    }

    public void RegisterUser(String number, ILogin iLogin){
        final int min = 10000;
        final int max = 99999;
        final int random = new Random().nextInt((max - min) + 1) + min;
        code =Integer.toString(random);
        RegisterRequest registerRequest = new RegisterRequest(number,code);
        ticketAPI.RegisterUser(registerRequest, new IResponse() {
            @Override
            public void onSuccess(Object response) {
                RegisterResponse registerResponse = (RegisterResponse) response;
                if(registerResponse.getSuccessful()){
                    iLogin.onSuccess(code);

                }else {
                    iLogin.onFailure("Erorr");
                }

            }

            @Override
            public void onFailuer(String errorMessage) {
                iLogin.onFailure(errorMessage);

            }
        });

    }

    public void forgotPassword(String number, ILogin iLogin){
        final int min = 10000;
        final int max = 99999;
        final int random = new Random().nextInt((max - min) + 1) + min;
        code =Integer.toString(random);
        RegisterRequest registerRequest = new RegisterRequest(number,code);
        ticketAPI.forgotPassword(registerRequest, new IResponse() {
            @Override
            public void onSuccess(Object response) {
                LoginResponse loginResponse = (LoginResponse) response;
                tokenClass.saveToken(loginResponse.getToken(),loginResponse.getName());
                iLogin.onSuccess(code);
            }
            @Override
            public void onFailuer(String errorMessage) {
                iLogin.onFailure(errorMessage);
            }
        });


    }

    public void SaveUser(String name,String number,String password, ILogin iLogin){
        SaveUser saveUser = new SaveUser(name,number,password);
        ticketAPI.SaveUser(saveUser, new IResponse() {
            @Override
            public void onSuccess(Object response) {
                TokenModel tokenModel = (TokenModel) response;
                tokenClass.saveToken(tokenModel.getToken(),name);
                iLogin.onSuccess(true);
            }

            @Override
            public void onFailuer(String errorMessage) {
                iLogin.onFailure(errorMessage);

            }
        });

    }

}
