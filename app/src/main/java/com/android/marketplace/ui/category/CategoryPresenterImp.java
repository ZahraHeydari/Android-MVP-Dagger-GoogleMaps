package com.android.marketplace.ui.category;

import android.annotation.SuppressLint;

import com.android.marketplace.data.model.Category;
import com.android.marketplace.data.source.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Listens to user actions from {@link CategoryFragment}, retrieves the data and updates the
 * UI as required.
 * <p>
 * Created by ZARA on 12/19/2018.
 */
public class CategoryPresenterImp implements CategoryPresenter {

    private static final String TAG = CategoryPresenterImp.class.getSimpleName();
    private final CategoryFragmentView mCategoryView;
    private final CategoryRepository mCategoryRepository;


    @SuppressLint("RestrictedApi")
    @Inject
    public CategoryPresenterImp(CategoryFragmentView categoryView,
                                CategoryRepository categoryRepository) {
        this.mCategoryView = checkNotNull(categoryView, "categoryView can not be null!");
        this.mCategoryRepository = checkNotNull(categoryRepository, "categoryRepository can not be null!");
    }

    @Override
    public void loadCategories() {
        mCategoryRepository.loadRootCategories(new CategoriesCallbackImp());
    }

    /**
     * To make an interaction between {@link CategoryPresenterImp}
     * and {@link CategoryRepository} that handles results
     */
    public class CategoriesCallbackImp {

        public void loadData(List<Category> categories) {
            mCategoryView.setResult(categories);
        }
    }
}