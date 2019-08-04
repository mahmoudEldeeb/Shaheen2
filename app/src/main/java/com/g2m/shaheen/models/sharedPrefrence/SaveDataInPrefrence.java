package com.g2m.shaheen.models.sharedPrefrence;

import android.content.Context;
import android.content.SharedPreferences;

import com.g2m.shaheen.utils.Constants;

public class SaveDataInPrefrence {

    public static SharedPreferences getPrefrence(){
        SharedPreferences preferences;
        preferences= Constants.context.getSharedPreferences("com.g2m.shaheen", Context.MODE_PRIVATE);
        return preferences;
    }
    public static SharedPreferences.Editor getEditor(){
        return   getPrefrence().edit();
    }
    public static void changeOperation(){
        int operation=getPrefrence().getInt("operation",0);
        operation++;
        getEditor().putInt("operation",operation).commit();
    }
    public static int getOperationNumber(){
       return getPrefrence().getInt("operation",0);
    }
}
