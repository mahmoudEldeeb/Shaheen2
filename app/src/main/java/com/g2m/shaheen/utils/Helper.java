package com.g2m.shaheen.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Helper {
    static ProgressDialog pd;
    public static void showDialog(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(Constants.context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface di, int i) {
                di.dismiss();
            }
        });
        builder.show();
    }

        public static boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) Constants.context.getSystemService(Constants.context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        public static void showDialog(){
            pd = new ProgressDialog(Constants.context);
            pd.setMessage("loading......");
            pd.show();
        }
        public static void dismiss(){
        pd.dismiss();
        }
}
