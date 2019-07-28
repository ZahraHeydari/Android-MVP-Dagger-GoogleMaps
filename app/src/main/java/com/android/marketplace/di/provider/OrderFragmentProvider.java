package com.android.marketplace.di.provider;


import com.android.marketplace.data.repository.OrderRepository;
import com.android.marketplace.data.source.local.AppDataBase;
import com.android.marketplace.ui.order.OrderFragment;
import com.android.marketplace.ui.order.OrderFragmentView;
import com.android.marketplace.ui.order.OrderPresenter;
import com.android.marketplace.ui.order.OrderPresenterImp;


import dagger.Module;
import dagger.Provides;

@Module
public class OrderFragmentProvider {

    @Provides
    static OrderPresenter provideOrderPresenter(OrderFragmentView orderFragmentView,
                                                OrderRepository orderRepository) {
        return new OrderPresenterImp(orderFragmentView, orderRepository);
    }

    @Provides
    OrderFragmentView provideOrderFragmentView(OrderFragment orderFragment) {
        return orderFragment;
    }

    @Provides
    OrderRepository provideOrderRepository(AppDataBase appDataBase) {
        return new OrderRepository(appDataBase);
    }
}
