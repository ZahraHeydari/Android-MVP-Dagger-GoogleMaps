package com.android.marketplace.ui.product;


import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.marketplace.R;
import com.android.marketplace.data.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter} to adapt
 * {@link com.android.marketplace.data.model.Product} items into {@link RecyclerView}
 * via {@link ProductViewHolder}
 *
 * Created by ZARA on 12/22/2018.
 */
public class ProductAdapter extends RecyclerView.Adapter {

    private static final String TAG = ProductAdapter.class.getSimpleName();
    private final ProductFragmentView mProductView;
    private final List<Product> mProducts;


    public ProductAdapter(ProductFragmentView productView, List<Product> products) {
        this.mProductView = productView;
        this.mProducts = products;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ProductViewHolder) holder).onBind(getItem(position));
    }

    private Product getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public int getItemCount() {
        if (mProducts == null) return 0;
        return mProducts.size();
    }

    /**
     * The holder of {@link Product}
     */
    public class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_text_view)
        TextView mTextView;
        @BindView(R.id.product_buy_button)
        AppCompatButton mBuyButton;

        public ProductViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /**
         * To bind {@link Product}
         *
         * @param product
         */
        public void onBind(final Product product) {
            mBuyButton.setOnClickListener(new OnBuyButtonClickListener(product));
            mTextView.setText(product.getTitle());
        }

        /**
         * When {@link Product} clicked
         */
        private class OnBuyButtonClickListener implements View.OnClickListener {

            private final Product product;

            public OnBuyButtonClickListener(Product product) {
                this.product = product;
            }

            @Override
            public void onClick(View view) {
                mProductView.gotoMapPageWithProduct(product);
            }
        }
    }
}