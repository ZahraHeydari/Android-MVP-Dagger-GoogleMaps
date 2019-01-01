package com.android.marketplace.di;

import com.android.marketplace.ui.category.CategoryFragment;
import com.android.marketplace.ui.category.CategoryFragmentModule;
import com.android.marketplace.ui.map.MapFragment;
import com.android.marketplace.ui.map.MapFragmentModule;
import com.android.marketplace.ui.order.OrderFragment;
import com.android.marketplace.ui.order.OrderFragmentModule;
import com.android.marketplace.ui.product.ProductFragment;
import com.android.marketplace.ui.product.ProductFragmentModule;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainActivityProviders {

    @ContributesAndroidInjector(modules = CategoryFragmentModule.class)
    abstract CategoryFragment provideCategoryFragment();

    @ContributesAndroidInjector(modules = ProductFragmentModule.class)
    abstract ProductFragment provideProductFragment();

    @ContributesAndroidInjector(modules = OrderFragmentModule.class)
    abstract OrderFragment provideOrderFragment();

    @ContributesAndroidInjector(modules = MapFragmentModule.class)
    abstract MapFragment provideMapFragment();

}