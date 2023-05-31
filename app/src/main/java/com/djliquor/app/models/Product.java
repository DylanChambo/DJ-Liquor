package com.djliquor.app.models;

import java.io.Serializable;

public class Product implements Serializable {

    private int idNumber;
    private float abv;
    private float cost;
    private String description;
    private Type category;
    private String imageAddress;
    private String name;

    private int numImages;
    public Product(int idNumber, float abv, float cost, String description, Type category, String imageAddress, String name) {
        this.idNumber = idNumber;
        this.abv = abv;
        this.cost = cost;
        this.description = description;
        this.category = category;
        this.imageAddress = imageAddress;
        this.name = name;
        this.numImages = 3;
    }

    public int getIdNumber() {
        return this.idNumber;
    }

    public float getAbv() {
        return this.abv;
    }

    public float getCost() {
        return this.cost;
    }

    public String getDescription() {
        return this.description;
    }

    public Type getCategory() {
        return this.category;
    }

    public String getImageAddress() {
        return this.imageAddress;
    }
    public String getName() { return this.name; }

    public int getNumImages() {return this.numImages; }

}
