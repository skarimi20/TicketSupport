package com.app.ticketsupport.ui.setting;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.ticketsupport.R;
import com.app.ticketsupport.serverConnection.TokenClass;
import com.app.ticketsupport.ui.login.ILogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EditUserFragment extends Fragment {

    @BindView(R.id.user_name)
    AppCompatEditText mUserName;
    @BindView(R.id.user_number)
    AppCompatEditText mUserNumber;
    @BindView(R.id.user_old_password)
    AppCompatEditText mUserOldPassword;
    @BindView(R.id.user_new_password)
    AppCompatEditText mUserNewPassword;
    @BindView(R.id.send_btn)
    FloatingActionButton mSendBtn;
    @BindView(R.id.cancel_action)
    FloatingActionButton mcancel;

    private String name,number;
    private SettingRepostory settingRepostory;

    public EditUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (getArguments() != null) {
            name = bundle.getString("name");
            number = bundle.getString("number");

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_edit_user, container, false);
        ButterKnife.bind(this,view);


        mUserName.setText(name);
        mUserNumber.setText(number);
        return view;

    }

    @OnClick(R.id.send_btn)
    public void setmSendBtn(){
        String name = mUserName.getText().toString();
        String number = mUserNumber.getText().toString();
        String oldPassword = mUserOldPassword.getText().toString();
        String newPassword = mUserNewPassword.getText().toString();
        if(name.isEmpty() || number.isEmpty()){
            Toast.makeText(getActivity(), "Please Enter name ans number", Toast.LENGTH_LONG).show();
        }else{
            settingRepostory = new SettingRepostory(getActivity());
            settingRepostory.updateUser(name, number, oldPassword, newPassword, new ILogin() {
                @Override
                public void onSuccess(Object response) {
                    Toast.makeText(getActivity(), "Your info successfully saved", Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction().remove(EditUserFragment.this).commitAllowingStateLoss();
                    changeName();



                }

                @Override
                public void onFailure(String Erorr) {
                    Toast.makeText(getActivity(), Erorr, Toast.LENGTH_LONG).show();


                }
            });

        }

    }

    @OnClick(R.id.cancel_action)
    public void setMcancel(){
        getFragmentManager().beginTransaction().remove(EditUserFragment.this).commitAllowingStateLoss();

    }

    public void changeName(){

        TokenClass tokenClass = new TokenClass(getActivity());
        tokenClass.setName(mUserName.getText().toString());
    }



}