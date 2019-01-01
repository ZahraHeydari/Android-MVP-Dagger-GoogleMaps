package com.android.marketplace.ui.category;


import com.android.marketplace.data.source.CategoryRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryFragmentModule {

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