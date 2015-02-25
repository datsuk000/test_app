package com.example.roman.test_app.data;

public class Category {
    private String mCategoryName;
    private String mCategoryId;

    public Category(String categoryName, String categoryId) {
        mCategoryName = categoryName;
        mCategoryId = categoryId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public String getCategoryId() {
        return mCategoryId;
    }
}
