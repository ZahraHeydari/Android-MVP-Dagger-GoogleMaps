package com.android.marketplace.ui.product;

import android.annotation.SuppressLint;

import com.android.marketplace.data.model.Product;
import com.android.marketplace.data.source.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Listens to user actions from {@link ProductFragment}, retrieves the data and updates the
 * UI as required.
 * <p>
 * Created by ZARA on 12/22/2018.
 */
public class ProductPresenterImp implements ProductPresenter {

    private static final String TAG = ProductPresenterImp.class.getSimpleName();
    private final ProductFragmentView mProductView;
    private final ProductRepository mProductRepository;


    @SuppressLint("RestrictedApi")
    @Inject
    public ProductPresenterImp(ProductFragmentView productView,
                               ProductRepository productRepository) {
        this.mProductView = checkNotNull(productView, "productView can not be null!");
        this.mProductRepository = checkNotNull(productRepository, "productRepository can not be null!");
    }

    @Override
    public void loadProductsByCategoryId(final int categoryId) {
        mProductRepository.loadProducts(categoryId, new ProductsCallbackImp());
    }


    /**
     * To make an interaction between {@link ProductPresenterImp}
     * and {@link ProductRepository}
     */
    public class ProductsCallbackImp {

        public void loadData(List<Product> products) {
            mProductView.setResult(products);
        }
    }
}