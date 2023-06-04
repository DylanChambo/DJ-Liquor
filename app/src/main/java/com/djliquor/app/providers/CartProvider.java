package com.djliquor.app.providers;

import android.content.Context;
import android.util.Pair;
import android.webkit.ConsoleMessage;

import com.djliquor.app.models.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartProvider {
    private static Map<Integer, Integer> cart = new LinkedHashMap<>();

    public static List<Pair<Product, Integer>> getCart(Context context)
    {
        List<Product> products = ProductProvider.generateData(context);
        List<Pair<Product, Integer>> cartList = new ArrayList<>();

        for (Integer id : cart.keySet())
        {
            cartList.add(new Pair<>(products.get(id), cart.get(id)));
        }

        return cartList;
    }

    public static void addToCart(int id, int num)
    {
        Integer current = cart.get(id);
        if (current == null)
        {
            current = 0;
        }
        cart.put(id, current + num);
    }

    public static void removeFromCart(Product product, int num)
    {
        Integer current = cart.get(product.getIdNumber());
        if (current == null)
        {
            current = 0;
        }
        int newNumber = current - num;
        if (newNumber < 0)
        {
            newNumber = 0;
        }
        cart.put(product.getIdNumber(), newNumber);
    }

    public static void clearCart()
    {
        cart.clear();
    }
}
