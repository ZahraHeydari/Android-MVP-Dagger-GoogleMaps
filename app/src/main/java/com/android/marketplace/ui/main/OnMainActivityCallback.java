package com.android.marketplace.ui.main;

import com.android.marketplace.data.model.Product;


public interface OnMainActivityCallback {

    /**
     * To go to {@link com.android.marketplace.ui.product.ProductFragment} with Category id
     *
     * @param categoryId
     */
    void gotoProductPageWithCategoryId(int categoryId);

    /**
     * To go to {@link com.android.marketplace.ui.map.MapFragment}
     *
     * @param product
     */
    void navigateToMapPage(Product product);

    /**
     * To open Order page via {@link com.android.marketplace.ui.order.OrderFragment}
     */
    void navigateToOrderPage();

}
