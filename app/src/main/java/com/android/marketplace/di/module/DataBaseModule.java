package com.android.marketplace.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.android.marketplace.data.source.local.AppDataBase;
import com.android.marketplace.data.source.local.dao.OrderDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    @Provides
    @Singleton
    AppDataBase provideRoomDatabase(Application application){
        return Room.databaseBuilder(
                application,
                AppDataBase.class,
                AppDataBase.DB_NAME)
                .allowMainThreadQueries().build();
    }


    @Provides
    OrderDao provideOrderDao(AppDataBase appDataBase){
        return appDataBase.getOrderDao();
    }
}
