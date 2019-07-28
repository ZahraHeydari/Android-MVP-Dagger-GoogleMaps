package com.android.marketplace.data.repository;

import com.android.marketplace.ui.category.CategoryPresenterImp;
import com.android.marketplace.data.model.Category;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * To handle data operations.
 * It knows where to get the data from Database or API calls to make when data is updated.
 * You can consider repositories to be mediators between different data sources, such as persistent models,
 * web services, and caches.
 *
 * @Author ZARA.
 */
public class CategoryRepository {

    /*
     * To generate the required category data.
     */
    private List<Category> generateFakeCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "NEW IN"));
        categoryList.add(new Category(2, "JACKETS & COATS"));
        categoryList.add(new Category(3, "JUMPERS & CARDIGANS"));
        categoryList.add(new Category(4, "T-SHIRTS"));
        categoryList.add(new Category(5, "SHIRTS"));
        categoryList.add(new Category(6, "DENIM"));
        categoryList.add(new Category(7, "TROUSERS & CHINOS"));
        categoryList.add(new Category(8, "HOODIES & SWEATSHIRTS"));
        categoryList.add(new Category(9, "FOOTWEAR"));
        categoryList.add(new Category(10, "ACCESSORIES"));
        return categoryList;
    }

    @Inject
    public CategoryRepository() {
    }

    /**
     * To load all {@link Category}s
     *
     * @param categoriesCallbackImp
     * @return
     */
    public void loadRootCategories(final CategoryPresenterImp.CategoriesCallbackImp categoriesCallbackImp) {
        categoriesCallbackImp.loadData(generateFakeCategories());
    }
}
