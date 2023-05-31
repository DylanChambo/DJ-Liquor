package com.djliquor.app.providers;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.djliquor.app.models.Product;
import com.djliquor.app.models.Type;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ProductProvider {


    static String[] names = {"Tiger", "Corona Extra", "Asahi", "Lion Red", "Heineken", "Export Citrus 0.0%", "Haagen", "Heineken 0.0%", "Speights Summit Ultra Low Carb", "Peroni",
    "Stone Paddock Hawkes Bay Syrah 2021", "Momo Organic Marlborough Sauvignon Blanc 2022", "Little Giant Barossa Shiraz 2021", "Elephant in the Room Langhorne Creek Shiraz 2020", "Domaine de la Vinconniere, Muscadet Sur Lie 2021", "Chateau Bois Pertuis, Bordeaux 2019", "Nero Oro Nero D'Avola Appassimento Sicilia DOC 2021", "Masseria Altemura 'Apulo' Primitivo 2019", "Milenrama Rioja Reserva 2016", "Three Finger Jack Old Vine Zinfandel 2019"};
    static int[] ids = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
    11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    static float[] abv = {5.00F, 4.50F, 5.00F, 4.00F, 5.00F, 0.00F, 4.00F, 0.00F, 4.20F, 5.10F,
            14.20F, 15.20F, 11.50F, 13.40F, 12.30F, 15.40F, 12.40F, 12.50F, 11.30F, 14.70F};
    static float[] cost = {1.49F, 2.49F, 3.49F, 1.49F, 2.99F, 3.49F, 2.99F, 1.49F, 2.79F, 3.59F,
    22.29F, 30.99F, 18.99F, 39.99F, 19.49F, 22.29F, 25.99F, 37.99F, 19.99F, 21.65F};
    static Type[] category = {Type.BEER, Type.BEER, Type.BEER, Type.BEER, Type.BEER, Type.BEER, Type.BEER, Type.BEER, Type.BEER, Type.BEER,
    Type.WINE, Type.WINE, Type.WINE, Type.WINE, Type.WINE, Type.WINE, Type.WINE, Type.WINE, Type.WINE, Type.WINE};
    static String[] imageAdd = {"tiger", "corona", "asahi", "lionred","heineken", "exportcitrus", "haagen", "heineken0", "speights", "peroni",
    "stonepaddock", "momo", "barossa", "elephant", "muscadet", "bordeaux", "sicilia", "masseria", "rioja", "zinfandel"};
    static String[] description = {"This is a Tiger beer", "This is a corona", "This is a bottle of Asahi", "This is a bottle of Lion Red", "This is a Heineken", "This is an Export Citrus Zero", "This is Haagen's classic lager.", "This is Heineken's zero-alcohol brew", "This is Speight's Summit Ultra Low Carb beer", "This is the classic Italian beer Peroni.",
    "this is a syrah", "this is a sauvignon blanc", "this is an Aussie Shiraz", "This is a shiraz", "This is a Muscadet", "This wine is from world-famous Bordeaux", "This wine is from the jewel of southern Italy: Sicily", "A fantastic wine", "A classic Spanish Rioja", "A classic Californian Zinfandel"};

    public static ArrayList<Product> generateData(Context context){
        ArrayList<Product> drinks = new ArrayList<Product>();
        Type[] types = Type.values();

        String json;
        try
        {
            InputStream is = context.getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Product newDrink = new Product(i,
                        (float)obj.getDouble("abv"),
                        (float)obj.getDouble("cost"),
                        obj.getString("description"),
                        types[obj.getInt("category")],
                        obj.getString("imageName"),
                        obj.getString("name"));
                drinks.add(newDrink);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return drinks;
    }


}
