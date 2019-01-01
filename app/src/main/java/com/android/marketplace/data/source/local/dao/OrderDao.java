package com.android.marketplace.data.source.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.android.marketplace.data.model.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM `ORDER`")
    List<Order> loadAll();

    @Query("SELECT * FROM `ORDER` WHERE id=:id")
    Order load(int id);

    @Query("SELECT * FROM `Order`")
    Cursor getOrderCursor();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Order order);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Insert
    void insertList(List<Order> orderList);
}
