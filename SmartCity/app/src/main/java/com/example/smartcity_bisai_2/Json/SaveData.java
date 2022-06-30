package com.example.smartcity_bisai_2.Json;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveData {
    public static boolean setLog(Context context,boolean log){
        SharedPreferences sp = context.getSharedPreferences("data1",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("log",log);
        editor.commit();
        return true;
    }
    public static boolean getLog(Context context){
        SharedPreferences sp = context.getSharedPreferences("data1",Context.MODE_PRIVATE);
        return sp.getBoolean("log",false);
    }

    public static boolean setToken(Context context,String Token){
        SharedPreferences sp = context.getSharedPreferences("data2",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Token",Token);
        editor.commit();
        return true;
    }
    public static String getToken(Context context){
        SharedPreferences sp = context.getSharedPreferences("data2",Context.MODE_PRIVATE);
        return sp.getString("Token",null);
    }
}
