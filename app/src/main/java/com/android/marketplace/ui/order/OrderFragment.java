package com.android.marketplace.ui.order;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.marketplace.R;
import com.android.marketplace.data.model.Order;
import com.android.marketplace.ui.main.OnMainActivityCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;


public class OrderFragment extends DaggerFragment implements OrderFragmentView {


    public static final String FRAGMENT_NAME = OrderFragment.class.getSimpleName();
    private static final String TAG = OrderFragment.class.getName();
    private static final long DELAY_TIME = 0;
    private static final long PERIOD_TIME = 5000;
    private View root;
    private List<Order> orderList = new ArrayList<>();
    @BindView(R.id.order_recycler_view)
    RecyclerView mRecyclerView;
    @Inject
    OrderPresenter mPresenter;
    private OrdersAdapter mAdapter;
    private OnMainActivityCallback mCallback;


    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainActivityCallback) {
            mCallback = (OnMainActivityCallback) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnMainActivityCallback!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, root);
        mAdapter = new OrdersAdapter(orderList);
        mRecyclerView.setAdapter(mAdapter);

        /*
         * Check every 5 seconds for the status of the order
         * (e.g. pending, in-process, delivery, and delivered)
         * by loading orders
         *
         * */
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getActivity() == null) return;
                getActivity().runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        // run main thread code
                        Log.i(TAG, "run: ");
                        updateOrderList();
                    }
                });
            }
        }, DELAY_TIME, PERIOD_TIME);

        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        root = null;
        mAdapter = null;
        mCallback = null;
    }

    @Override
    public void setResult(List<Order> orders) {
        Log.d(TAG, "setResult() called with: orders = [" + orders + "]");
        orderList.clear();
        orderList.addAll(orders);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }


    public void updateOrderList() {
        mPresenter.loadOrders();
    }
}
