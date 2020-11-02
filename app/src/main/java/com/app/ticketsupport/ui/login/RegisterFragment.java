package com.app.ticketsupport.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.ticketsupport.MainActivity;
import com.app.ticketsupport.R;
import com.app.ticketsupport.models.RegisterRequest;
import com.app.ticketsupport.models.RegisterResponse;
import com.app.ticketsupport.serverConnection.TokenClass;
import com.app.ticketsupport.ui.setting.DarkmodeClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterFragment extends Fragment {


    @BindView(R.id.edt_number)
    AppCompatEditText mNumber;
    @BindView(R.id.edt_name)
    AppCompatEditText mName;
    @BindView(R.id.edt_code)
    AppCompatEditText mCode;
    @BindView(R.id.btn_register)
    Button mRegister;
    @BindView(R.id.btn_code)
    Button btnCodeSend;
    @BindView(R.id.progressRel)
    RelativeLayout mProgressBar;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.progress_text)
    AppCompatTextView mProgressText;
    @BindView(R.id.edt_password)
    AppCompatEditText mPassword;
    @BindView(R.id.loginTxt)
    AppCompatTextView loginTxt;
    @BindView(R.id.forgetTxt)
    AppCompatTextView forgetTxt;

    private LoginRepository loginRepository;
    private String code = "";


    public RegisterFragment() {
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
        View view =inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this,view);
        loginRepository = new LoginRepository(getActivity());
        darkMode();
        return view;
    }

    @OnClick(R.id.btn_code)
    public void setBtnCodeSend(){
        mProgressBar.setVisibility(View.VISIBLE);
        final String mobileNumber;

        mobileNumber = mNumber.getText().toString();
        loginRepository.RegisterUser(mobileNumber, new ILogin() {
            @Override
            public void onSuccess(Object response) {
                code = (String) response;
                progressBar.setVisibility(View.GONE);
                mProgressText.setText("Message Successfully sent");
                mCode.animate()
                        .alpha(1.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mCode.setVisibility(View.VISIBLE);
                                mRegister.setVisibility(View.VISIBLE);
                                btnCodeSend.setVisibility(View.GONE);

                            }
                        });
            }
            @Override
            public void onFailure(String Erorr) {
                progressBar.setVisibility(View.GONE);
                mProgressText.setText(Erorr);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }, 1500);


            }
        });


    }

    @OnClick(R.id.btn_register)
    public void setmRegister(){
        mProgressBar.setVisibility(View.VISIBLE);
        final String code;
        code = mCode.getText().toString();
        if(code != null){
            final String sCode = mCode.getText().toString();
            if(sCode.equals(code)){
                final String name = mName.getText().toString();
                final String password = mPassword.getText().toString();
                final String number = mNumber.getText().toString();
                loginRepository.SaveUser(name, number, password, new ILogin() {
                    @Override
                    public void onSuccess(Object response) {
                        progressBar.setVisibility(View.GONE);
                        mProgressText.setText("SuccessFully Registered");
                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(getActivity(), MainActivity.class);
                                LoginActivity loginActivity = new LoginActivity();
                                loginActivity.finish();
                                startActivity(i);
                            }
                        }, 1500);
                    }

                    @Override
                    public void onFailure(String Erorr) {
                        progressBar.setVisibility(View.GONE);
                        mProgressText.setText(Erorr);
                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setVisibility(View.GONE);
                            }
                        }, 1500);
                    }
                });

                //save
            }else if(sCode != code){
                mProgressText.setText("Code is Wrong!");
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }, 1500);

            }else{
                mProgressText.setText("Please Enter Mobile Number first!");
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }, 1500);

            }
        }


    }

    public void darkMode(){
        DarkmodeClass darkmodeClass = new DarkmodeClass(getActivity());
        if(darkmodeClass.getSetting()){
            loginTxt.setTextColor(getResources().getColor(R.color.white));
            forgetTxt.setTextColor(getResources().getColor(R.color.white));
            btnCodeSend.setTextColor(getResources().getColor(R.color.black));
            btnCodeSend.setBackgroundColor(getResources().getColor(R.color.white));
            mRegister.setTextColor(getResources().getColor(R.color.black));
            mRegister.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }
}