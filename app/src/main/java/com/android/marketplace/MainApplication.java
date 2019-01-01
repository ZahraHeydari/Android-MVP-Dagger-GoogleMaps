package com.android.marketplace;


import android.content.Context;
import android.support.multidex.MultiDex;

import com.android.marketplace.di.AppComponent;
import com.android.marketplace.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class MainApplication extends DaggerApplication {

    private static final String TAG = MainApplication.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
