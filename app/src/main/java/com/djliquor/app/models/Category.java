package com.djliquor.app.models;

public class Category {
    private String imageFileName;
    private String categoryName;
    private Type type;

    public Category(String categoryName, String imageFileName, Type type)
    {
        this.categoryName = categoryName;
        this.imageFileName = imageFileName;
        this.type = type;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Type getType() {return type;}
}
