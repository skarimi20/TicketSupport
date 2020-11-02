package com.app.ticketsupport.ui.ticket;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.ticketsupport.R;
import com.app.ticketsupport.ui.setting.EditUserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AboutUsFragment extends Fragment {

    @BindView(R.id.button)
    AppCompatButton button;
    @BindView(R.id.version)
    AppCompatTextView version;


    public AboutUsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this,view);

        version.setText(String.valueOf("Version: "+ getCurrentVersion()));


        return view;
    }

    @OnClick(R.id.button)
    public void setButton(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:s_karimi20@outlook.com"));
        startActivity(emailIntent);
    }

    @OnClick(R.id.cancel_button)
    public void setcancel(){
        getFragmentManager().beginTransaction().remove(AboutUsFragment.this).commitAllowingStateLoss();
    }

    public int getCurrentVersion(){
        int verCode = 0;
        try {
            PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            verCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }

}