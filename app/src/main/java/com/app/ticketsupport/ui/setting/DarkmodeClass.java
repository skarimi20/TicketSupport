package com.app.ticketsupport.ui.setting;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
/**
 * Save and Read DARK MODE Setting
 */
public class DarkmodeClass {

    Context context;


    public DarkmodeClass(Context context) {
        this.context = context;
    }


    public void setSetting(){
        SharedPreferences.Editor editor = context.getSharedPreferences("dark_mode", MODE_PRIVATE).edit();
        if (getSetting()){
            editor.putBoolean("isDark", false);
        }else {
            editor.putBoolean("isDark", true);
        }

        editor.apply();
    }

    public Boolean getSetting(){
        SharedPreferences prefs = context.getSharedPreferences("dark_mode", MODE_PRIVATE);
        Boolean isDark = prefs.getBoolean("isDark", false);
        return isDark;
    }

}
