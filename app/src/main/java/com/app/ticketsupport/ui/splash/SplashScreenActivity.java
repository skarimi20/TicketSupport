package com.app.ticketsupport.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.app.ticketsupport.MainActivity;
import com.app.ticketsupport.R;
import com.app.ticketsupport.serverConnection.IResponse;
import com.app.ticketsupport.serverConnection.TicketAPI;
import com.app.ticketsupport.serverConnection.TokenClass;
import com.app.ticketsupport.ui.login.LoginActivity;
import com.app.ticketsupport.ui.login.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Check for Internet Connection and User Login Status and start Required Activity
 */
public class SplashScreenActivity extends AppCompatActivity {

    private TicketAPI ticketAPI;
    private TokenClass tokenClass;

    @BindView(R.id.textview)
    AppCompatTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        tokenClass = new TokenClass(getApplicationContext());
        ticketAPI = new TicketAPI();
        textView.setText("Connecting to Server...");
        ticketAPI.checkConnection(getToken(),new IResponse() {
            @Override
            public void onSuccess(Object response) {
                if (isLogin()){
                    start(1);
                }else {
                    start(0);
                }
            }
            @Override
            public void onFailuer(String errorMessage) {
                if (!errorMessage.equals("Error")){
                    textView.setText("No Internet connection!");
                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finishAffinity();
                        }
                    }, 3000);

                }else {
                    start(0);
                }
            }
        });
    }

    public void start(int i){
        if (i == 1) {
            textView.setText("Welcome back...");
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }, 1000);
        }else if (i == 0) {
            textView.setText("Welcome to TICKET SUPPORT");
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }
            }, 1000);
        }
    }

    public Boolean isLogin(){
        return tokenClass.isLoging();
    }

    public String getToken(){
        return tokenClass.getToken().getToken();
    }


}