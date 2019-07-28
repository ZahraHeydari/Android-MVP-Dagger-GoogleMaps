package com.android.marketplace.di.provider;


import com.android.marketplace.data.repository.ProductRepository;
import com.android.marketplace.ui.product.ProductFragment;
import com.android.marketplace.ui.product.ProductFragmentView;
import com.android.marketplace.ui.product.ProductPresenter;
import com.android.marketplace.ui.product.ProductPresenterImp;


import dagger.Module;
import dagger.Provides;

@Module
public class ProductFragmentProvider {

    @Provides
    static ProductPresenter provideProductPresenter(ProductFragmentView productFragmentView,
                                                    ProductRepository productRepository) {
        return new ProductPresenterImp(productFragmentView, productRepository);
    }

    @Provides
    ProductFragmentView provideProductFragmentView(ProductFragment productFragment) {
        return productFragment;
    }
}
