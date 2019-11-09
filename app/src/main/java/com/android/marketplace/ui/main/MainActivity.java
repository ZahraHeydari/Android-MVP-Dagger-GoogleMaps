package com.android.marketplace.ui.main;


import android.arch.lifecycle.LifecycleOwner;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.marketplace.R;
import com.android.marketplace.data.model.Product;
import com.android.marketplace.ui.category.CategoryFragment;
import com.android.marketplace.ui.map.MapFragment;
import com.android.marketplace.ui.order.OrderFragment;
import com.android.marketplace.ui.product.ProductFragment;
import com.android.marketplace.util.ActivityUtils;
import com.android.marketplace.util.OrderStatusService;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;


public class MainActivity extends DaggerAppCompatActivity implements OnMainActivityCallback, LifecycleOwner {


    private static final String TAG = MainActivity.class.getName();
    @BindView(R.id.tab_layout)
    public TabLayout mTabLayout;
    private Intent intentService;
    private static final int ORDERS_TAB_INDEX = 1;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected() called with: name = [" + name + "], service = [" + service + "]");
            ((OrderStatusService.MyBinder) service).getService().isNeededToUpdate.observe(MainActivity.this, aBoolean -> {
                Log.d(TAG, "onChanged() called with: aBoolean = [" + aBoolean + "]");
                if (aBoolean != null && aBoolean) {
                    changeOrdersStatus();
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected() called with: name = [" + name + "]");

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        intentService = new Intent(MainActivity.this, OrderStatusService.class);

        if (savedInstanceState == null) replaceByCategoryFragment();//set as a default
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.categories)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.orders)));
        mTabLayout.addOnTabSelectedListener(new OnMainTabSelectedListener());

        registerService();

    }

    public void registerService() {
        startService(intentService);
        bindService(intentService, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unregisterService() {
        stopService(intentService);
        unbindService(mServiceConnection);
    }


    private void manageTabs(TabLayout.Tab tab) {
        Log.d(TAG, "manageTabs() called with: tab = [" + tab.getPosition() + "]");
        switch (tab.getPosition()) {
            default:
            case 0:
                replaceByCategoryFragment();
                break;
            case 1:
                replaceByOrderFragment();
                break;
        }
    }

    private void replaceByOrderFragment() {
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(),
                OrderFragment.newInstance(),
                OrderFragment.FRAGMENT_NAME,
                false,
                R.id.main_container);
    }

    private void replaceByCategoryFragment() {
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(),
                CategoryFragment.newInstance(),
                CategoryFragment.FRAGMENT_NAME,
                false,
                R.id.main_container);
    }


    @Override
    public void gotoProductPageWithCategoryId(int categoryId) {
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(),
                ProductFragment.newInstance(categoryId),
                ProductFragment.FRAGMENT_NAME,
                true,
                R.id.main_container
        );
    }

    @Override
    public void navigateToMapPage(Product product) {
        ActivityUtils.replaceFragmentInActivity(
                getSupportFragmentManager(),
                MapFragment.newInstance(product),
                MapFragment.FRAGMENT_NAME,
                true,
                R.id.main_container);
    }

    @Override
    public void navigateToOrderPage() {
        TabLayout.Tab tab = mTabLayout.getTabAt(ORDERS_TAB_INDEX);
        if (tab != null) {
            tab.select();
        }
        replaceByOrderFragment();
    }

    public void changeOrdersStatus() {
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(OrderFragment.FRAGMENT_NAME);
        if (fragmentByTag instanceof OrderFragment) {
            ((OrderFragment) fragmentByTag).updateOrderList();
        }

    }

    private class OnMainTabSelectedListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Log.d(TAG, "onTabSelected() called with: tab = [" + tab + "]");
            manageTabs(tab);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            Log.d(TAG, "onTabUnselected() called with: tab = [" + tab + "]");
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            Log.d(TAG, "onTabReselected() called with: tab = [" + tab + "]");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterService();
    }
}
