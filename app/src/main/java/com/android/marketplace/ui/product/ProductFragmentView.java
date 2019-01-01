package com.android.marketplace.ui.product;


import com.android.marketplace.data.model.Product;

import java.util.List;

/**
 * The view of {@link ProductFragment}
 *
 * Created by ZARA on 12/22/2018.
 */
public interface ProductFragmentView {

    /**
     * To set result of response(data) in {@link ProductFragment}
     *
     * @param products
     */
    void setResult(List<Product> products);


    /**
     * To go to {@link com.android.marketplace.ui.map.MapFragment}
     * with {@link Product}
     *
     * @param product
     */
    void gotoMapPageWithProduct(Product product);
}
