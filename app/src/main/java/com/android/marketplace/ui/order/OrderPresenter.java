package com.android.marketplace.ui.order;

import com.android.marketplace.data.model.Order;

/**
 *
 * To handle all user actions that are related to {@link OrderFragment}
 *
 * Created by ZARA on 12/23/2018.
 */
public interface OrderPresenter {

    /**
     * to load all {@link Order}s
     *
     * @return
     */
    void loadOrders();
}
