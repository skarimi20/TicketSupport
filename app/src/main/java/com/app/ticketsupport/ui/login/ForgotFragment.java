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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.ticketsupport.MainActivity;
import com.app.ticketsupport.R;
import com.app.ticketsupport.ui.setting.DarkmodeClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ForgotFragment extends Fragment {

    @BindView(R.id.edt_number)
    AppCompatEditText mNumber;
    @BindView(R.id.edt_code)
    AppCompatEditText mCode;
    @BindView(R.id.btn_login)
    Button mLogin;
    @BindView(R.id.btn_send)
    Button mSendCode;
    @BindView(R.id.progressRel)
    RelativeLayout mProgressBar;
    @BindView(R.id.progress_text)
    AppCompatTextView mProgressText;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.register_txt)
    AppCompatTextView register_txt;


    private String code = "";
    private LoginRepository loginRepository;


    public ForgotFragment() {
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
        View view  = inflater.inflate(R.layout.fragment_forgot, container, false);
        ButterKnife.bind(this,view);
        darkMode();
        return view;
    }

    @OnClick(R.id.btn_send)
    public void sendmSendCode(){
        mProgressBar.setVisibility(View.VISIBLE);
        loginRepository = new LoginRepository(getActivity());
        String number = mNumber.getText().toString();
        loginRepository.forgotPassword(number, new ILogin() {
            @Override
            public void onSuccess(Object response) {
                mProgressBar.setVisibility(View.GONE);
                mCode.animate()
                        .alpha(1.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mCode.setVisibility(View.VISIBLE);
                                mSendCode.setVisibility(View.GONE);
                            }
                        });
                code = (String) response;
                mLogin.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Code successfully sent!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(String Erorr) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), Erorr, Toast.LENGTH_SHORT).show();



            }
        });
    }


    @OnClick(R.id.btn_login)
    public void setmLogin() {
        mProgressBar.setVisibility(View.VISIBLE);
        final String userCode;
        userCode = mCode.getText().toString();

        if (userCode.equals(code)){
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();
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
        }else{
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Code is Wrong!", Toast.LENGTH_SHORT).show();
        }

    }

    public void darkMode(){
        DarkmodeClass darkmodeClass = new DarkmodeClass(getActivity());
        if(darkmodeClass.getSetting()){
            register_txt.setTextColor(getResources().getColor(R.color.white));
            mSendCode.setTextColor(getResources().getColor(R.color.black));
            mSendCode.setBackgroundColor(getResources().getColor(R.color.white));
            mLogin.setTextColor(getResources().getColor(R.color.black));
            mLogin.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }




}