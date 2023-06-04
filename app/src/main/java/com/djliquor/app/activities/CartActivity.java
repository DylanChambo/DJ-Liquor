package com.djliquor.app.activities;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.djliquor.app.R;
import com.djliquor.app.adaptors.CartAdaptor;
import com.djliquor.app.adaptors.CategoryAdaptor;
import com.djliquor.app.databinding.ActivityCartBinding;
import com.djliquor.app.models.Category;
import com.djliquor.app.models.Product;
import com.djliquor.app.providers.CartProvider;
import com.djliquor.app.providers.CategoryProvider;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class CartActivity extends AppCompatActivity {

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

        ListView cartView = (ListView) this.findViewById(R.id.cart_list);

        CartAdaptor cartAdapter = new CartAdaptor(this, R.layout.cart_list_view_item,
                CartProvider.getCart(this));
        cartView.setAdapter(cartAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
