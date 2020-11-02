package com.app.ticketsupport.serverConnection;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.app.ticketsupport.models.VersionModel;
import com.app.ticketsupport.ui.update.NewVersionActivity;

/**
 * Compare the Value of Current version and Server Version and if not equal, GET the New version Download Link and send to NewVersionActivity
 */

public class CheckForUpdate {

    private TicketAPI ticketAPI;
    private Context context;


    public CheckForUpdate(Context context){
        ticketAPI = new TicketAPI();
        this.context = context;
    }

    public void isUpDate(){

        ticketAPI.getAppVersion(new IResponse() {
            @Override
            public void onSuccess(Object response) {
                VersionModel versionModel = (VersionModel) response;
                if (versionModel.getLastVersion() == getCurrentVersion()){
                    Toast.makeText(context, "Application is Up to date", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(context, "New version "+versionModel.getLastVersion()+" is Available!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, NewVersionActivity.class);
                    intent.putExtra("version", versionModel.getLastVersion());
                    intent.putExtra("link", versionModel.getDownloadLink());
                    context.startActivity(intent);
                }

            }

            @Override
            public void onFailuer(String errorMessage) {

            }
        });

    }

    public int getCurrentVersion(){
        int verCode = 0;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            verCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }
}
