package com.android.marketplace.ui.product;


import com.android.marketplace.data.source.ProductRepository;


import dagger.Module;
import dagger.Provides;

@Module
public class ProductFragmentModule {

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
