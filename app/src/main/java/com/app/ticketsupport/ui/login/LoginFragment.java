package com.app.ticketsupport.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.app.ticketsupport.MainActivity;
import com.app.ticketsupport.R;
import com.app.ticketsupport.models.LoginResponse;
import com.app.ticketsupport.serverConnection.TokenClass;
import com.app.ticketsupport.ui.setting.DarkmodeClass;
import com.app.ticketsupport.ui.setting.EditUserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends Fragment {

    @BindView(R.id.edt_number)
    AppCompatEditText mNumber;
    @BindView(R.id.edt_pass)
    AppCompatEditText mPassword;
    @BindView(R.id.btn_login)
    Button bLogin;
    @BindView(R.id.progressRel)
    RelativeLayout mProgressBar;
    @BindView(R.id.progress_text)
    AppCompatTextView mProgressText;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.register_txt)
    AppCompatTextView registerTxt;


    private LoginRepository loginRepository;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        darkMode();
        loginRepository = new LoginRepository(getActivity());




        return view;

    }

    @OnClick(R.id.btn_login)
    public void setbLogin(){
        mProgressBar.setVisibility(View.VISIBLE);
        String number, password;
        number = mNumber.getText().toString();
        password = mPassword.getText().toString();


        loginRepository.LoginUser(number, password, new ILogin() {
            @Override
            public void onSuccess(Object response) {
                LoginResponse loginResponse = (LoginResponse) response;
                mProgressText.setText("Welcome Back " + loginResponse.getName());

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);

                    }
                }, 1500);

            }

            @Override
            public void onFailure(String Erorr) {
                progressBar.setVisibility(View.GONE);
                mProgressText.setText("Mobile Number or Password is wrong!");
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }, 1000);


            }
        });
    }



    public void darkMode(){
        DarkmodeClass darkmodeClass = new DarkmodeClass(getActivity());
        if(darkmodeClass.getSetting()){
            registerTxt.setTextColor(getResources().getColor(R.color.white));
            bLogin.setBackgroundColor(getResources().getColor(R.color.white));
            bLogin.setTextColor(getResources().getColor(R.color.black));
        }

    }








}