package com.android.marketplace.ui.category;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.marketplace.R;
import com.android.marketplace.data.model.Category;
import com.android.marketplace.ui.main.OnMainActivityCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;


public class CategoryFragment extends DaggerFragment implements CategoryFragmentView {

    public static final String FRAGMENT_NAME = CategoryFragment.class.getSimpleName();
    private static final String TAG = CategoryFragment.class.getName();
    private View root;
    private List<Category> categoryList = new ArrayList<>();
    @BindView(R.id.list_recycler_view)
    RecyclerView mRecyclerView;
    @Inject
    CategoryPresenter mPresenter;
    private CategoryAdapter mAdapter;
    private OnMainActivityCallback mCallback;


    public static CategoryFragment newInstance() {
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
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
        mPresenter.loadCategories();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, root);

        mAdapter = new CategoryAdapter(this, categoryList);
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
    public void setResult(List<Category> categories) {
        categoryList.addAll(categories);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void navigateToProductPageWithCategoryId(int id) {
          mCallback.gotoProductPageWithCategoryId(id);
    }
}
