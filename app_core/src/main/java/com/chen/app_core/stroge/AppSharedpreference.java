package com.chen.app_core.stroge;

import android.content.Context;
import android.content.SharedPreferences;

import com.chen.app_core.app.AppCore;

public class AppSharedpreference {

    public static final SharedPreferences SHARED_PREFERENCES = AppCore.getApplication().getSharedPreferences("appconfig", Context.MODE_PRIVATE);

    enum AppConfig{
        ISFIRSTOPEN,
        ISLOGIN,
        TOKEN,
    }

    public static boolean isFirstOpenApp(){
       return SHARED_PREFERENCES.getBoolean(AppConfig.ISFIRSTOPEN.name(),true);
    }

    public static void setISFirstopen(boolean isFirstopen){
        SHARED_PREFERENCES.edit()
                .putBoolean(AppConfig.ISFIRSTOPEN.name(),isFirstopen)
                .apply();
    }

    public static boolean isLogin(){
        return SHARED_PREFERENCES.getBoolean(AppConfig.ISLOGIN.name(),false);
    }

    public static void setisLogin(boolean islogin){
        SHARED_PREFERENCES.edit()
                .putBoolean(AppConfig.ISLOGIN.name(),islogin)
                .apply();
    }
}
