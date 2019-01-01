package com.android.marketplace.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import com.android.marketplace.data.model.Order;
import com.android.marketplace.data.source.local.dao.OrderDao;

@Database(entities = {Order.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {

    public static final String DB_NAME = "marketPlaceDatabase.db";

    public abstract OrderDao getOrderDao();

}
