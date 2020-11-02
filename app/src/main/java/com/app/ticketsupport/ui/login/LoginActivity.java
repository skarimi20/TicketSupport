package com.app.ticketsupport.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.ticketsupport.R;
import com.app.ticketsupport.models.LoginResponse;
import com.app.ticketsupport.ui.setting.DarkmodeClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager2 viewPager2;
    @BindView(R.id.container)
    RelativeLayout relativeLayout;
    @BindView(R.id.title)
    AppCompatTextView title;


    private static final int NUM_PAGES = 3;

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private ForgotFragment forgotFragment;
    private FragmentAdapter fragmentAdapter;

    private int close ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        darkMode();
        fragmentAdapter = new FragmentAdapter(LoginActivity.this);
        viewPager2.setAdapter(fragmentAdapter);
        viewPager2.setPageTransformer(new DepthPageTransformer());

    }


    public void darkMode(){
        DarkmodeClass darkmodeClass = new DarkmodeClass(getApplicationContext());
        if(darkmodeClass.getSetting()){
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.black));
            title.setTextColor(getResources().getColor(R.color.white));
        }

    }


    public void onBackPressed() {
        if(close == 0){
            Toast.makeText(this, "Press one more time to close!", Toast.LENGTH_LONG).show();
            close += 1;
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    close =0;
                }
            }, 3000);

        }else {
            finishAffinity();
            close = 0;
        }

    }
}