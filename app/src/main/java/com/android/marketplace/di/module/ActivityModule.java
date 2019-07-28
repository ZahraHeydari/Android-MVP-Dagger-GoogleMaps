package com.android.marketplace.di.module;

import com.android.marketplace.di.provider.CategoryFragmentProvider;
import com.android.marketplace.di.provider.MapFragmentProvider;
import com.android.marketplace.di.provider.OrderFragmentProvider;
import com.android.marketplace.di.provider.ProductFragmentProvider;
import com.android.marketplace.ui.category.CategoryFragment;
import com.android.marketplace.ui.map.MapFragment;
import com.android.marketplace.ui.order.OrderFragment;
import com.android.marketplace.ui.product.ProductFragment;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = CategoryFragmentProvider.class)
    abstract CategoryFragment provideCategoryFragment();

    @ContributesAndroidInjector(modules = ProductFragmentProvider.class)
    abstract ProductFragment provideProductFragment();

    @ContributesAndroidInjector(modules = OrderFragmentProvider.class)
    abstract OrderFragment provideOrderFragment();

    @ContributesAndroidInjector(modules = MapFragmentProvider.class)
    abstract MapFragment provideMapFragment();

}