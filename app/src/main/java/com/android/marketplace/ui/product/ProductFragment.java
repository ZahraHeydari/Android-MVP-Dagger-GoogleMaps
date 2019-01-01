package com.android.marketplace.ui.product;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.marketplace.R;
import com.android.marketplace.data.model.Product;
import com.android.marketplace.ui.main.OnMainActivityCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;


public class ProductFragment extends DaggerFragment implements ProductFragmentView {

    public static final String FRAGMENT_NAME = ProductFragment.class.getSimpleName();
    private static final String TAG = ProductFragment.class.getName();
    private static final String CATEGORY_ID_KEY = "CATEGORY_ID_KEY";
    private View root;
    private List<Product> productList = new ArrayList<>();
    @BindView(R.id.list_recycler_view)
    RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private OnMainActivityCallback mCallback;
    private int mCategoryId;
    @Inject
    ProductPresenter mPresenter;

    public static ProductFragment newInstance(int categoryId) {
        Bundle args = new Bundle();
        args.putInt(CATEGORY_ID_KEY, categoryId);
        ProductFragment fragment = new ProductFragment();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(CATEGORY_ID_KEY)) {
            mCategoryId = getArguments().getInt(CATEGORY_ID_KEY);
            mPresenter.loadProductsByCategoryId(mCategoryId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, root);

        mAdapter = new ProductAdapter(this, productList);
        mRecyclerView.setAdapter(mAdapter);

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
    public void setResult(List<Product> products) {
        productList.addAll(products);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gotoMapPageWithProduct(Product product) {
        mCallback.navigateToMapPage(product);
    }


}
