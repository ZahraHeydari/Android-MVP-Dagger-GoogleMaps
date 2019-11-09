package com.android.marketplace.util;

import android.app.Service;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class OrderStatusService extends Service {

    private String TAG = OrderStatusService.class.getName();
    public MutableLiveData<Boolean> isNeededToUpdate = new MutableLiveData<>();
    public Binder mBinder = new MyBinder();
    private long PERIOD_TIME = 30000L;
    private long DELAY_TIME = 0;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*
         * Check every 30 seconds for changing the status of orders
         * if there is still main activity
         *
         * hint: did not check the size of order list yet!!
         */
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isNeededToUpdate.postValue(true);
            }

        }, DELAY_TIME, PERIOD_TIME);
    }


    public class MyBinder extends Binder {
        public OrderStatusService getService() {
            return OrderStatusService.this;
        }
    }


}
