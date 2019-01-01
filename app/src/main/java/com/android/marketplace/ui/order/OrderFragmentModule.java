package com.android.marketplace.ui.order;


import com.android.marketplace.data.source.OrderRepository;
import com.android.marketplace.data.source.local.AppDataBase;


import dagger.Module;
import dagger.Provides;

@Module
public class OrderFragmentModule {

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
