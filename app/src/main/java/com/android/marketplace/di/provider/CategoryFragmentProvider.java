package com.android.marketplace.di.provider;


import com.android.marketplace.data.repository.CategoryRepository;
import com.android.marketplace.ui.category.CategoryFragment;
import com.android.marketplace.ui.category.CategoryFragmentView;
import com.android.marketplace.ui.category.CategoryPresenter;
import com.android.marketplace.ui.category.CategoryPresenterImp;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryFragmentProvider {

    @Provides
    static CategoryPresenter provideCategoryPresenter(CategoryFragmentView categoryFragmentView,
                                                      CategoryRepository categoryRepository) {
        return new CategoryPresenterImp(categoryFragmentView, categoryRepository);
    }

    @Provides
    CategoryFragmentView provideCategoryFragmentView(CategoryFragment categoryFragment) {
        return categoryFragment;
    }

}