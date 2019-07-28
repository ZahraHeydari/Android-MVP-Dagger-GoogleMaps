package com.android.marketplace.data.repository;

import com.android.marketplace.data.model.Product;
import com.android.marketplace.ui.product.ProductPresenterImp;

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
public class ProductRepository {

    private List<Product> productList = new ArrayList<>();

    @Inject
    public ProductRepository() {
        generateFakeProducts();
    }


    public void generateFakeProducts() {
        productList.add(new Product(1, 1, "4 Pack Mind Brown Crew Nick T-Shirts"));
        productList.add(new Product(2, 1, "Black Paris Sport Slogan Sweatshirt"));
        productList.add(new Product(3, 1, "Navy Long Sleeve Muscle Fit Shirt"));
        productList.add(new Product(4, 1, "Pink Pocket Front Long Sleeve Hoodie"));
        productList.add(new Product(5, 1, "Black Slim Fit Blazer"));
        productList.add(new Product(6, 2, "Camel Overcoat"));
        productList.add(new Product(7, 2, "Dark Grey Overcoat"));
        productList.add(new Product(8, 2, "Brown Slim Fit Blazer"));
        productList.add(new Product(9, 3, "Teal Colour Black Jumper"));
        productList.add(new Product(10, 3, "Off White Blocked Knit Jumper"));
        productList.add(new Product(11, 4, "Green Polo Shirt"));
        productList.add(new Product(12, 5, "Black Revere Collar Short Sleeve Shirt"));
        productList.add(new Product(13, 5, "White Muscle Fit Stretch Shirt"));
        productList.add(new Product(14, 6, "Bright Blue Crop Slim Jeans"));
        productList.add(new Product(15, 6, "Navy Rinse Skinny Stretch Jeans"));
        productList.add(new Product(16, 6, "Plus Size Black Denim Jacket"));
        productList.add(new Product(17, 6, "Black Denim Jacket"));
        productList.add(new Product(18, 7, "Grey Pinstripe Pull-On Trousers"));
        productList.add(new Product(19, 7, "Tan Side Piped Trousers"));
        productList.add(new Product(20, 8, "Black Paradox Slogan Sweatshirt"));
        productList.add(new Product(21, 8, "Mid Pink Pocket Front Hoodie"));
        productList.add(new Product(22, 8, "Rust Zip Up Funnel Neck Sweatshirt"));
        productList.add(new Product(23, 8, "Orange Colour Black Hoodie"));
        productList.add(new Product(24, 8, "Black Pocket Front Hoodie"));
        productList.add(new Product(25, 9, "White Panelled Chunky Trainers"));
        productList.add(new Product(26, 9, "Black Combat Boots"));
        productList.add(new Product(27, 9, "Black Embossed Shoes"));
        productList.add(new Product(28, 10, "Black Stripe Belt"));
        productList.add(new Product(29, 10, "Abyss Fragrance"));
        productList.add(new Product(30, 10, "Mercury Fragrance"));
        productList.add(new Product(31, 10, "Carbon Fragrance"));
        productList.add(new Product(32, 10, "Black Braces"));
        productList.add(new Product(33, 10, "Burgundy Velvet Cap"));
        productList.add(new Product(34, 10, "Black Tie"));
        productList.add(new Product(35, 10, "Navy Tie"));
        productList.add(new Product(36, 10, "Dark Red Tie"));
        productList.add(new Product(37, 10, "Grey Check Flat Cap"));
        productList.add(new Product(38, 10, "Harry Potter Egg Cup Set"));
        productList.add(new Product(39, 10, "Silver Braces"));
        productList.add(new Product(40, 10, "Golden Braces"));
    }

    /**
     * To load all {@link com.android.marketplace.data.model.Product}s
     *
     * @param categoryId
     * @param productsCallbackImp
     * @return
     */
    public void loadProducts(final int categoryId, final ProductPresenterImp.ProductsCallbackImp productsCallbackImp) {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategoryId() == categoryId) {
                products.add(product);
            }
        }
        productsCallbackImp.loadData(products);
    }
}
