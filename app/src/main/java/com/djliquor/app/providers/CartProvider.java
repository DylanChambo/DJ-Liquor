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

    public static int addToCart(int id, int num)
    {
        Integer current = cart.get(id);
        if (current == null)
        {
            current = 0;
        }
        int total = current + num;
        if (total >= 99) {
            total = 99;
        }
        cart.put(id, total);
        return total;
    }

    public static int removeFromCart(int id, int num)
    {
        Integer current = cart.get(id);
        if (current == null)
        {
            current = 0;
        }
        int newNumber = current - num;
        if (newNumber <= 0)
        {
            cart.remove(id);
            return 0;
        } else
        {
            cart.put(id, newNumber);
            return newNumber;
        }

    }

    public static float getTotal(Context context)
    {
        float total = 0f;
        List<Pair<Product, Integer>> cartList = getCart(context);
        for (Pair<Product, Integer> cartItem : cartList) {
            total += cartItem.first.getCost() * cartItem.second;
        }
        return total;
    }
}
