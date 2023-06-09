package com.djliquor.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.SearchView;

import com.djliquor.app.R;
import com.djliquor.app.adaptors.ImagePagerAdaptor;
import com.djliquor.app.databinding.ActivityDetailBinding;
import com.djliquor.app.models.Product;
import com.djliquor.app.providers.CartProvider;

import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SearchView searchView = (SearchView) this.findViewById(R.id.search_view);
        ImageView backButton = (ImageView) this.findViewById(R.id.back_button);

        ViewPager imageView = (ViewPager) this.findViewById(R.id.image_view_pager);
        TextView nameTextView = (TextView) this.findViewById(R.id.name_text_view);
        TextView priceTextView = (TextView) this.findViewById(R.id.price_text_view);
        TextView abvTextView = (TextView) this.findViewById(R.id.abv_text_view);
        TextView descTextView = (TextView) this.findViewById(R.id.desc_text_view);
        Button addToCart = (Button) this.findViewById(R.id.add_to_cart);
        ImageView cartButton = (ImageView) this.findViewById(R.id.cart);

        searchView.setVisibility(View.INVISIBLE);

        Product product = getIntent().getSerializableExtra("product", Product.class);
        int numImages = product.getNumImages();
        int[] images = new int[numImages];
        int id;
        for (int i = 0; i < numImages; i++)
        {
            id = getResources().getIdentifier(product.getImageAddress() + i,
                    "drawable", getPackageName());
            if (id == 0)
            {
                id = getResources().getIdentifier("baseline_image_24",
                        "drawable", getPackageName());
            }
            images[i]= id;
        }

        ImagePagerAdaptor ViewPagerAdaptor = new ImagePagerAdaptor(DetailActivity.this, images);
        imageView.setAdapter(ViewPagerAdaptor);

        nameTextView.setText(product.getName());
        priceTextView.setText("$" + product.getCost());
        abvTextView.setText(product.getAbv() + "% ABV");
        descTextView.setText(product.getDescription());

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartProvider.addToCart(product.getIdNumber(), 1);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}
