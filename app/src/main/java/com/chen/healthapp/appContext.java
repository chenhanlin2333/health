package com.chen.healthapp;

import android.app.Application;

import com.chen.app_core.app.AppCore;
import com.raizlabs.android.dbflow.config.FlowManager;

public class appContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.init(this)
                .withApiHost("http://127.0.0.1/")
                .configure();
        FlowManager.init(this);

    }
}
