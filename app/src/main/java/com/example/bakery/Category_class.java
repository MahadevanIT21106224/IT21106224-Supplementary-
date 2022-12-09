package com.example.bakery;

public class Category_class {

    private String CategoryId;
    private String CategoryName;

    public Category_class() {
;
    }


    public Category_class(String categoryId, String categoryName) {
        CategoryId = categoryId;
        CategoryName = categoryName;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
