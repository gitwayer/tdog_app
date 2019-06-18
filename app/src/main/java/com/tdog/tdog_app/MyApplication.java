package com.tdog.tdog_app;

import android.app.Application;

import com.tdog.tdog_app.net.HttpHelper;
import com.tdog.tdog_app.net.OkHttpProcessor;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.init(new OkHttpProcessor());
    }
}
