package com.android.marketplace.ui.category;

import com.android.marketplace.data.model.Category;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 * <p>
 * Created by ZARA on 12/19/2018.
 */
public interface CategoryFragmentView {

    /**
     * To set result of response(data) in {@link CategoryFragment}
     *
     * @param categories
     */
    void setResult(List<Category> categories);


    /**
     * To load {@link com.android.marketplace.data.model.Product}s of category by its id
     *
     * @param id
     */
    void navigateToProductPageWithCategoryId(int id);
}
