package com.android.marketplace.di.builder;

import com.android.marketplace.di.module.ActivityModule;
import com.android.marketplace.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract MainActivity bindMainActivity();

}