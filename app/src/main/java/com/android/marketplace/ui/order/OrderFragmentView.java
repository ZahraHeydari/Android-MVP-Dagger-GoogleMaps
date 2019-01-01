package com.android.marketplace.ui.order;

import com.android.marketplace.data.model.Order;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 *
 * Created by ZARA on 12/22/2018.
 */
public interface OrderFragmentView {

    /**
     * To set result of response(data) in {@link OrderFragment}
     *
     * @param orders
     */
    void setResult(List<Order> orders);

}
