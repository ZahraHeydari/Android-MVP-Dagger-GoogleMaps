package com.android.marketplace.ui.category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.marketplace.R;
import com.android.marketplace.data.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter} to adapt
 * {@link Category} items into {@link RecyclerView} via {@link CategoryViewHolder}
 *
 * Created by ZARA on 12/19/2018.
 */
public class CategoryAdapter extends RecyclerView.Adapter {

    private static final String TAG = CategoryAdapter.class.getSimpleName();
    private final CategoryFragmentView mCategoryView;
    private final List<Category> mCategories;


    public CategoryAdapter(CategoryFragmentView categoryView, List<Category> categories) {
        this.mCategoryView = categoryView;
        this.mCategories = categories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CategoryViewHolder) holder).onBind(getItem(position));
    }

    private Category getItem(int position) {
        return mCategories.get(position);
    }

    @Override
    public int getItemCount() {
        if (mCategories == null) return 0;
        return mCategories.size();
    }

    /**
     * holder of {@link Category}
     */
    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_text_view)
        TextView mTextView;

        public CategoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /**
         * to bind {@link Category}
         *
         * @param category
         */
        public void onBind(final Category category) {
            itemView.setOnClickListener(new OnRootCategoryClickListener(category));
            mTextView.setText(category.getTitle());
        }

        /**
         * when {@link Category} clicked
         */
        private class OnRootCategoryClickListener implements View.OnClickListener {

            private final Category category;

            public OnRootCategoryClickListener(Category category) {
                this.category = category;
            }

            @Override
            public void onClick(View view) {
                mCategoryView.navigateToProductPageWithCategoryId(category.getId());
            }
        }
    }
}