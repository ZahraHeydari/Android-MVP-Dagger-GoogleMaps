package com.android.marketplace.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.android.marketplace.R;
import com.android.marketplace.ui.category.CategoryFragment;
import com.android.marketplace.data.model.Product;
import com.android.marketplace.ui.map.MapFragment;
import com.android.marketplace.ui.order.OrderFragment;
import com.android.marketplace.ui.product.ProductFragment;
import com.android.marketplace.util.ActivityUtils;


import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;


public class MainActivity extends DaggerAppCompatActivity implements OnMainActivityCallback {


    private static final String TAG = MainActivity.class.getName();
    @BindView(R.id.tab_layout)
    public TabLayout mTabLayout;
    private static final int ORDERS_TAB_INDEX = 1;
    private static final long DELAY_TIME = 0;
    private static final long PERIOD_TIME = 30000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) replaceByCategoryFragment();//set as a default
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.categories)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.orders)));
        mTabLayout.addOnTabSelectedListener(new OnMainTabSelectedListener());

        /*
         * Check every 30 seconds for changing the order status
         * if there is still main activity
         */
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        // run main thread code
                        changeOrdersStatus();
                    }
                });
            }
        }, DELAY_TIME, PERIOD_TIME);

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
}
