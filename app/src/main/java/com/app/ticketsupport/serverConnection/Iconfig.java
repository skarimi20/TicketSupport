package com.app.ticketsupport.serverConnection;

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

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Iconfig {


    @POST("api/auth")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("api/register")
    Call<RegisterResponse> RegisterUser (@Body RegisterRequest registerRequest);

    @POST("api/register/user")
    Call<TokenModel> SaveUser (@Body SaveUser saveUser);

    @POST("api/ticket/new")
    Call<IdModel> NewTicket (@Header("x-auth-token") String token, @Body TicketRequest ticketRequest);

    @GET("api/ticket/my")
    Call<List<TicketModel>> getTickets(@Header("x-auth-token") String token);

    @POST("api/ticket/answer")
    Call<ResponseBody> sendAnswer(@Header("x-auth-token") String token,@Body AnswerRequest answerRequest);

    @POST("api/forgot")
    Call<LoginResponse> forgetPassword(@Body RegisterRequest registerRequest);

    @GET("api/user/me")
    Call<SettingUserModel> getUser(@Header("x-auth-token") String token);

    @POST("api/user/update")
    Call<ResponseBody> updateUser(@Header("x-auth-token") String token, @Body UpdateUserModel updateUserModel);

    @GET("api/version")
    Call<VersionModel> getAppVersion();

    @GET("api/version/check")
    Call<ResponseBody> checkConnection(@Header("x-auth-token") String token);




}
