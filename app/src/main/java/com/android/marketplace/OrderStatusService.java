package com.android.marketplace;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.marketplace.ui.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;


public class OrderStatusService extends Service {

    private static final String TAG = OrderStatusService.class.getName();
    private static final long DELAY_TIME = 0;
    private static final long PERIOD_TIME = 30000;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");

        /*
         * Check every 30 seconds for changing the order status
         * if there is still main activity
         */
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                MainActivity activity = MainActivity.instance;
                if (activity == null) return;
                activity.runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        // run main thread code
                        activity.changeOrdersStatus();
                    }
                });
            }
        }, DELAY_TIME, PERIOD_TIME);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
