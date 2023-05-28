package com.djliquor.app.models;

public class Category {
    private String imageFileName;
    private String categoryName;

    public Category(String categoryName, String imageFileName)
    {
        this.categoryName = categoryName;
        this.imageFileName = imageFileName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
