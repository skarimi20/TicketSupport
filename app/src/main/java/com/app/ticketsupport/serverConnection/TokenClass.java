package com.app.ticketsupport.serverConnection;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.app.ticketsupport.models.TokenModel;

import static android.content.Context.MODE_PRIVATE;

/**
 * Save and Read Token and Username to SharedPreferences
 */
public class TokenClass {
    private Context context;

    public TokenClass(Context context){
        this.context =context;

    }

    public TokenModel getToken(){
        SharedPreferences prefs = context.getSharedPreferences("config", MODE_PRIVATE);
        Boolean isLoging = prefs.getBoolean("isLogin", false);
        String Token = prefs.getString("token", "");
        TokenModel tokenModel = new TokenModel(Token);
        return tokenModel;
    }

    public void saveToken(String token, String name){
        SharedPreferences.Editor editor = context.getSharedPreferences("config", MODE_PRIVATE).edit();
        editor.putBoolean("isLogin", true);
        editor.putString("name",name);
        editor.putString("token", token);
        editor.apply();
    }

    public Boolean isLoging (){
        SharedPreferences prefs = context.getSharedPreferences("config", MODE_PRIVATE);
        Boolean isLoging = prefs.getBoolean("isLogin", false);
        return isLoging;
    }

    public String getName(){
        SharedPreferences prefs = context.getSharedPreferences("config", MODE_PRIVATE);
        Boolean isLoging = prefs.getBoolean("isLogin", false);

        if(!isLoging){
            Toast.makeText(context, "you not Loged in!", Toast.LENGTH_SHORT).show();
        }
        String name = prefs.getString("name", "");
        return name;
    }

    public void setName(String name){
        SharedPreferences.Editor editor = context.getSharedPreferences("config", MODE_PRIVATE).edit();
        editor.putString("name",name);
        editor.apply();
    }

    public void Logout(){
        SharedPreferences.Editor editor = context.getSharedPreferences("config", MODE_PRIVATE).edit();
        editor.putBoolean("isLogin", false);
        editor.putString("name",null);
        editor.putString("token", null);
        editor.apply();
    }

}
