package com.android.marketplace.data.source;


import com.android.marketplace.data.model.Order;
import com.android.marketplace.data.source.local.AppDataBase;
import com.android.marketplace.ui.order.OrderPresenterImp;

import javax.inject.Inject;

/**
 * To handle data operations.
 * It knows where to get the data from Database or API calls to make when data is updated.
 * You can consider repositories to be mediators between different data sources, such as persistent models,
 * web services, and caches.
 *
 * @Author ZARA.
 */
public class OrderRepository {

    private static final String TAG = OrderRepository.class.getName();
    private AppDataBase mAppDataBase;

    @Inject
    public OrderRepository(AppDataBase appDataBase) {
        mAppDataBase = appDataBase;
    }

    /**
     * To load all {@link com.android.marketplace.data.model.Order}s
     *
     * @param ordersCallbackImp
     * @return
     */
    public void loadOrders(final OrderPresenterImp.OrdersCallbackImp ordersCallbackImp) {
        ordersCallbackImp.loadData(mAppDataBase.getOrderDao().loadAll());
    }

    public void insertOrder(Order order) {
        mAppDataBase.getOrderDao().insert(order);
    }
}
