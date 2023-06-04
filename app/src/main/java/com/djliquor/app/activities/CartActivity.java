package com.djliquor.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.djliquor.app.R;
import com.djliquor.app.databinding.ActivityCartBinding;
import com.djliquor.app.models.Product;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CartActivity extends AppCompatActivity {

    private static Map<Integer, Integer> cart = new LinkedHashMap<>();

    private ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SearchView searchView = (SearchView) this.findViewById(R.id.search_view);
        ImageView backButton = (ImageView) this.findViewById(R.id.back_button);
        ImageView cartButton = (ImageView) this.findViewById(R.id.cart);

        searchView.setVisibility(View.INVISIBLE);
        cartButton.setVisibility(View.INVISIBLE);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void addToCart(Product product, int num)
    {
        Integer current = cart.get(product.getIdNumber());
        if (current == null)
        {
            current = 0;
        }
        cart.put(product.getIdNumber(), current + num);
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
