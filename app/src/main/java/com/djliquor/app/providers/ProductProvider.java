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
                        obj.getString("name"),
                        obj.getInt("numImages"));
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
