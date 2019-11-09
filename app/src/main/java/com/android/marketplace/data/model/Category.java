package com.android.marketplace.data.model;


public class Category {

    private int id;
    private String title;

   /* static {
        System.out.println("Category");
    }

    public Category() {
        System.out.println("Constructor");
    }*/

    public Category(int id, String title) {
        System.out.println("2nd Constructor");
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
