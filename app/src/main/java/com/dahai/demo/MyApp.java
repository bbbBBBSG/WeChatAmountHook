package com.dahai.demo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MyApp extends Application {

    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();

        preferences = getSharedPreferences("DH_HOOK", Context.MODE_PRIVATE);
    }

    public static void saveAmount(String amount) {
        preferences.edit().putString("Amount",amount).apply();
    }

    public static String getAmount() {
        Log.e("HHH", "getAmount: " + (preferences==null) );
        return preferences.getString("Amount", "");
    }
}
