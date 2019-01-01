package com.android.marketplace.di;

import com.android.marketplace.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityProviders.class})
    abstract MainActivity bindMainActivity();

}