package com.android.marketplace.data.model;

import org.parceler.Parcel;

import javax.inject.Inject;

@Parcel
public class Product {

    private int id;
    private int categoryId;
    private String title;

    public Product() {
    }

    public Product(int id, int categoryId, String title) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                '}';
    }
}
