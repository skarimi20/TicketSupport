package com.app.ticketsupport.ui.update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.app.ticketsupport.R;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * show Newst Version Code and New version Download Link For User
 */
public class NewVersionActivity extends AppCompatActivity {

    @BindView(R.id.version)
    AppCompatTextView mVersion;

    @BindView(R.id.download_btn)
    AppCompatButton mDownlaod;

    private Long version;
    private String Dlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_version);
        ButterKnife.bind(this);

        version = getIntent().getLongExtra("version",0);
        Dlink = getIntent().getStringExtra("link");

        mVersion.setText(String.valueOf(version));



    }

    @OnClick(R.id.download_btn)
    public void setmDownlaod(){

            String url = Dlink;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

    }
}