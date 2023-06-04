package com.djliquor.app.activities;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import com.djliquor.app.providers.ProductProvider;

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
        TextView cartTotal = (TextView) this.findViewById(R.id.cart_total);

        searchView.setVisibility(View.INVISIBLE);
        cartButton.setVisibility(View.INVISIBLE);

        ListView cartView = (ListView) this.findViewById(R.id.cart_list);
        TextView cartEmpty = (TextView) this.findViewById(R.id.cart_empty);

        List<Pair<Product,Integer>> cart = CartProvider.getCart(this);
        int length = cart.size();
        if (length > 0) {
            cartEmpty.setVisibility(View.INVISIBLE);
        } else
        {
            cartView.setVisibility(View.INVISIBLE);
        }

        CartAdaptor cartAdapter = new CartAdaptor(this, R.layout.cart_list_view_item,
                cart);
        cartView.setAdapter(cartAdapter);

        cartTotal.setText(String.format("TOTAL: $%.2f", CartProvider.getTotal(this)));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
