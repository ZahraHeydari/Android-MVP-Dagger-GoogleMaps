package com.android.marketplace.ui.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.marketplace.R;
import com.android.marketplace.data.model.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter} to adapt
 * {@link Order} items into {@link RecyclerView} via {@link OrderViewHolder}
 *
 * Created by ZARA on 12/22/2018.
 */
public class OrdersAdapter extends RecyclerView.Adapter {


    private static final String TAG = OrdersAdapter.class.getSimpleName();
    private final OrderFragmentView mOrderView;
    private final List<Order> mOrders;


    public OrdersAdapter(OrderFragmentView orderView, List<Order> orders) {
        this.mOrderView = orderView;
        this.mOrders = orders;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((OrderViewHolder) holder).onBind(getItem(position));
    }

    private Order getItem(int position) {
        return mOrders.get(position);
    }

    @Override
    public int getItemCount() {
        if (mOrders == null) return 0;
        return mOrders.size();
    }

    /**
     * holder of {@link Order}
     */
    public class OrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.order_title_text_view)
        TextView mTitleTextView;
        @BindView(R.id.order_address_text_view)
        TextView mAddressTextView;
        @BindView(R.id.order_status_text_view)
        TextView mStatusTextView;

        public OrderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /**
         * To bind {@link Order}
         *
         * @param order
         */
        public void onBind(final Order order) {
            mTitleTextView.setText(order.getTitle());
            mAddressTextView.setText(order.getFormattedAddressLine());
            mStatusTextView.setText(order.getFormattedStatus());
        }
    }
}