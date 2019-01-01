package com.android.marketplace.ui.product;

import com.android.marketplace.data.model.Product;

/**
 * To handle all user actions that are related to {@link ProductFragment}
 */
public interface ProductPresenter {

    /**
     * To load all {@link Product}s by categoryId
     *
     * @param categoryId
     * @return
     */
    void loadProductsByCategoryId(int categoryId);
}
