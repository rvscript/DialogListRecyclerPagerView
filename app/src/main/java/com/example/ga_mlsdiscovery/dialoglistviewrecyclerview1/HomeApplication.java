package com.example.ga_mlsdiscovery.dialoglistviewrecyclerview1;

import android.app.Application;

import timber.log.Timber;

public class HomeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
