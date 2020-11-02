package com.app.ticketsupport.serverConnection;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.app.ticketsupport.models.AnswerRequest;
import com.app.ticketsupport.models.IdModel;
import com.app.ticketsupport.models.LoginRequest;
import com.app.ticketsupport.models.LoginResponse;
import com.app.ticketsupport.models.RegisterRequest;
import com.app.ticketsupport.models.RegisterResponse;
import com.app.ticketsupport.models.SaveUser;
import com.app.ticketsupport.models.SettingUserModel;
import com.app.ticketsupport.models.TicketModel;
import com.app.ticketsupport.models.TicketRequest;
import com.app.ticketsupport.models.TokenModel;
import com.app.ticketsupport.models.UpdateUserModel;
import com.app.ticketsupport.models.UserModel;
import com.app.ticketsupport.models.VersionModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketAPI {

    private Iconfig iconfig;
    public TicketAPI(){
        iconfig = TicketClient.getRetrofit().create(Iconfig.class);
    }
    /**
     * Send MobileNumber and password to server to Auth, if auth successfully Receive response.code = 200 , Users name and Token for Authorization
     */
    public void loginUser(LoginRequest loginRequest, IResponse iResponse){
        Call<LoginResponse> call = iconfig.loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 200){
                    iResponse.onSuccess(response.body());
                }else{
                    iResponse.onFailuer("Number or Password is Wrong!");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());


            }
        });
    }
    /**
     * Send MobileNumber and Code that Generate in ui.login.LoginRepository to Server and server send SMS that Code in Customize temple to user Mobile number
     * (First check if User number not registered Already)
     */
    public void RegisterUser(RegisterRequest registerRequest , IResponse iResponse){
        Call<RegisterResponse> call = iconfig.RegisterUser(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.code() == 200){
                    iResponse.onSuccess(response.body());
                }else{
                    iResponse.onFailuer("User Already Registered");
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());


            }
        });
    }
    /**
     * compere the Generate Code with input code and if equal, send MobileNumber , name and password to server for create new user and receive Token for Authorization
     */
    public void SaveUser(SaveUser saveUser , IResponse iResponse){
        Call<TokenModel> call = iconfig.SaveUser(saveUser);
        call.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                if(response.code() == 200){
                    iResponse.onSuccess(response.body());
                }else {
                    iResponse.onFailuer("Name or Number or Password is Wrong!");
                }
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());

            }
        });
    }
    /**
     * Send Tickets values and Token to server and receive Saved Ticket id for answers Activity
     * Token: include User_ID for Authorization, more info in www.jwt.io
     */
    public void NewTicket(String Token ,TicketRequest ticketRequest , IResponse iResponse){
        Call<IdModel> call = iconfig.NewTicket(Token, ticketRequest);
        call.enqueue(new Callback<IdModel>() {
            @Override
            public void onResponse(Call<IdModel> call, Response<IdModel> response) {
                if(response.code() == 200 ){
                    iResponse.onSuccess(response.body());
                }else if(response.code() == 403){
                    iResponse.onFailuer("Wrong Token, Login Again");
                }else {
                    iResponse.onFailuer("Error, Check again");
                }

            }

            @Override
            public void onFailure(Call<IdModel> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());


            }
        });
    }
    /**
     * Send Token Receive List of User Tickets for TicketFragment
     */
    public void getTickets(String Token, IResponse iResponse){
        Call<List<TicketModel>> TicketsData = iconfig.getTickets(Token);
        TicketsData.enqueue(new Callback<List<TicketModel>>() {
            @Override
            public void onResponse(Call<List<TicketModel>> call, Response<List<TicketModel>> response) {
                if(response.code() == 200){
                    iResponse.onSuccess(response.body());
                }else {
                    iResponse.onFailuer("Error");
                }
            }

            @Override
            public void onFailure(Call<List<TicketModel>> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());

            }
        });

    }
    /**
     * Send Token, ticket id and answer String to server, if response.code = 200 mean Successfully sent otherwise failed
     */
    public void SendAnswer(String Token, AnswerRequest answerRequest, IResponse iResponse){
        Call<ResponseBody> call = iconfig.sendAnswer(Token,answerRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200){
                    iResponse.onSuccess(response.body());
                }else {
                    iResponse.onFailuer("Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());

            }
        });
    }
    /**
     * Send MobileNumber and Code that Generate in ui.login.LoginRepository to Server and server send SMS that Code in Customize temple to user Mobile number
     * (First check if User number registered)
     *  compere the Generate Code with input code and if equal, receive Token and name and Login
     */
    public void forgotPassword(RegisterRequest registerRequest, IResponse iResponse){
        Call<LoginResponse> call = iconfig.forgetPassword(registerRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 200){
                    iResponse.onSuccess(response.body());
                }else if(response.code() == 404){
                    iResponse.onFailuer("this number not Registered");
                }else {
                    iResponse.onFailuer("Error");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());

            }
        });
    }
    /**
     * Send Token that Include User_ID and receive User Info (Number,name, ...) for SettingFragment
     */
    public void getUser(String Token,IResponse iResponse){
        Call<SettingUserModel> call = iconfig.getUser(Token);
        call.enqueue(new Callback<SettingUserModel>() {
            @Override
            public void onResponse(Call<SettingUserModel> call, Response<SettingUserModel> response) {
                if(response.code() == 200){
                    iResponse.onSuccess(response.body());
                }else {
                    iResponse.onFailuer("Error");
                }
            }

            @Override
            public void onFailure(Call<SettingUserModel> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());
            }
        });
    }
    /**
     * IF(user just change name and number) send Name and Number to server to change and if success receive response.code=200
     * IF(user want change name and number (and,or) Password) Send new Password and Old password and server compare current password with old password if equal = update user
     */
    public void updateUser(String Token, UpdateUserModel updateUserModel, IResponse iResponse){
        Call<ResponseBody> call = iconfig.updateUser(Token,updateUserModel);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200){
                    iResponse.onSuccess(response.body());
                }else {
                    iResponse.onFailuer("Your password is incorrect");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());

            }
        });

    }
    /**
     * Get newest Version number and New Version Download Link
     */
    public void getAppVersion(IResponse iResponse){
        Call<VersionModel> call = iconfig.getAppVersion();
        call.enqueue(new Callback<VersionModel>() {
            @Override
            public void onResponse(Call<VersionModel> call, Response<VersionModel> response) {

                iResponse.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<VersionModel> call, Throwable t) {

                iResponse.onFailuer(t.getMessage().toString());

            }
        });
    }
    /**
     * Send Token to server for First Check connection, Second Check if user register or not in SplashScreenActivity
     */
    public void checkConnection(String Token,IResponse iResponse){
        Call<ResponseBody> call = iconfig.checkConnection(Token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200){
                    iResponse.onSuccess(response.body());
                }else {
                    iResponse.onFailuer("Error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                iResponse.onFailuer(t.getMessage().toString());
            }
        });
    }



}
