package com.djliquor.app.providers;

import java.util.ArrayList;

import com.djliquor.app.models.Product;
import com.djliquor.app.models.Type;


public class ProductProvider {

    static String[] names = {"Tiger", "Corona Extra", "Asahi", "Lion Red", "Heineken"};
    static int[] ids = {001, 002, 003, 004, 005};
    static float[] abv = {5.00F, 4.50F, 5.00F, 4.00F, 5.00F};
    static float[] cost = {1.49F, 2.49F, 3.49F, 1.49F, 2.99F};
    static Type[] category = {Type.BEER, Type.BEER, Type.BEER, Type.BEER, Type.BEER};
    static String[] imageAdd = {"tiger", "corona", "asahi", "lionred","heineken"};
    static String[] description = {"This is a Tiger beer", "This is a corona", "This is a bottle of Asahi", "This is a bottle of Lion Red", "This is a Heineken"};

    public static ArrayList<Product> generateData(){
        ArrayList<Product> drinks = new ArrayList<Product>();

        for (int i = 0; i<5; i++) {
            Product newDrink = new Product(ids[i], abv[i], cost[i], description[i], category[i], imageAdd[i], names[i]);
            drinks.add(newDrink);
        }

        return drinks;

    }


}
