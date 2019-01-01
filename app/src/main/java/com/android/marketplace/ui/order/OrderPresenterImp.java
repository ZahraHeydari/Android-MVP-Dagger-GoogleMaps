package com.android.marketplace.ui.order;


import android.annotation.SuppressLint;
import android.util.Log;

import com.android.marketplace.data.model.Order;
import com.android.marketplace.data.source.OrderRepository;

import java.util.List;

import javax.inject.Inject;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Listens to user actions from {@link OrderFragment}, retrieves the data and updates the
 * UI as required.
 * <p>
 * Created by ZARA on 12/22/2018.
 */
public class OrderPresenterImp implements OrderPresenter {

    private static final String TAG = OrderPresenterImp.class.getName();
    private final OrderFragmentView mOrderView;
    private final OrderRepository mOrderRepository;

    @SuppressLint("RestrictedApi")
    @Inject
    public OrderPresenterImp(OrderFragmentView orderView,
                             OrderRepository orderRepository) {
        mOrderView = checkNotNull(orderView, "orderView can not be null!");
        mOrderRepository = checkNotNull(orderRepository, "orderRepository can not be null!");
    }

    @Override
    public void loadOrders() {
        mOrderRepository.loadOrders(new OrdersCallbackImp());
    }

    /**
     * To make an interaction between {@link OrderPresenterImp}
     * and {@link OrderRepository} and handle the results
     */
    public class OrdersCallbackImp {

        public void loadData(List<Order> orders) {
            Log.d(TAG, "loadData() called with: orders = [" + orders + "]");
            mOrderView.setResult(orders);
        }
    }
}
