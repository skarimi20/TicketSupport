package com.app.ticketsupport.ui.setting;

import android.content.Context;

import com.app.ticketsupport.models.SettingUserModel;
import com.app.ticketsupport.models.UpdateUserModel;
import com.app.ticketsupport.models.UserModel;
import com.app.ticketsupport.serverConnection.IResponse;
import com.app.ticketsupport.serverConnection.TicketAPI;
import com.app.ticketsupport.serverConnection.TokenClass;
import com.app.ticketsupport.ui.login.ILogin;
/**
 * Get Values from View and make require changes for serverConnection.TicketAPI
 */
public class SettingRepostory {
    private Context context;
    private TokenClass tokenClass;
    private TicketAPI ticketAPI;
    public SettingRepostory (Context context){
        tokenClass = new TokenClass(context);
        ticketAPI = new TicketAPI();
    }

    public void getUser(ILogin iLogin){
        ticketAPI.getUser(getToken(),new IResponse() {
            @Override
            public void onSuccess(Object response) {
                SettingUserModel userModel = (SettingUserModel) response;
                iLogin.onSuccess(userModel);
            }

            @Override
            public void onFailuer(String errorMessage) {
                iLogin.onFailure(errorMessage);
            }
        });
    }

    public void updateUser(String name,String number, String oldPassword, String newPassword, ILogin iLogin){
        UpdateUserModel updateUserModel = null;
        if (oldPassword.isEmpty() || newPassword.isEmpty()){
            updateUserModel = new UpdateUserModel(name,number);
        }else {
            updateUserModel = new UpdateUserModel(name, number, newPassword, oldPassword);
        }

        ticketAPI.updateUser(getToken(), updateUserModel, new IResponse() {
            @Override
            public void onSuccess(Object response) {
                iLogin.onSuccess(response);
            }

            @Override
            public void onFailuer(String errorMessage) {
                iLogin.onFailure(errorMessage);

            }
        });
    }


    public void logout(){
        tokenClass.Logout();
    }
    public String getToken(){
        return tokenClass.getToken().getToken();
    }
}


