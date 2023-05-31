package com.djliquor.app.activities;

import static com.djliquor.app.providers.ProductProvider.generateData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.djliquor.app.intefaces.IProductView;
import com.djliquor.app.R;
import com.djliquor.app.adaptors.ProductAdaptor;
import com.djliquor.app.databinding.ActivityListBinding;
import com.djliquor.app.databinding.ActivityMainBinding;
import com.djliquor.app.models.Product;
import com.djliquor.app.models.Type;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements IProductView {

    private ActivityListBinding binding;
    private Type category;

    private ArrayList<Product> searchResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SearchView searchView = (SearchView) this.findViewById(R.id.search_view);
        TextView tv = this.findViewById(R.id.search_text);
        ImageView backButton = (ImageView) this.findViewById(R.id.back_button);

        String search = getIntent().getExtras().getString("search");
        if (search == null) {
            search = "";
        } else {
            tv.setText("RESULTS FOR `" + search.toUpperCase() + "`");
        }

        category = getIntent().getSerializableExtra("category", Type.class);
        if (category == null) {
            category = Type.None;
        } else {
            tv.setText("ALL " + category);
        }


        searchView.setQuery(search, false);

        updateFilter(search, category);

        String finalSearch = search;
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery(finalSearch, false);
                setBackButtonSearch(backButton, searchView);
                searchView.startAnimation(AnimationUtils.loadAnimation(
                        ListActivity.this, R.anim.righttoleft));
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setBackButtonTraverse(backButton);
                searchView.startAnimation(AnimationUtils.loadAnimation(
                        ListActivity.this, R.anim.lefttoright));
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tv.setText("RESULTS FOR `" + query.toUpperCase() + "`");
                updateFilter(query, category);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        setBackButtonTraverse(backButton);
    }

    void setBackButtonSearch(ImageView backButton, SearchView searchView)
    {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery("", false);
                searchView.setIconified(true);
            }
        });
    }

    void setBackButtonTraverse(ImageView backButton)
    {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void updateFilter(String query, Type category) {
        searchResults = generateData(this);
        if (query != "")
        {
            searchResults.removeIf(product -> !product.getName().toLowerCase().contains(query.toLowerCase()));
        }

        if (category != Type.None)
        {
            searchResults.removeIf(product -> !(product.getCategory() == category));
        }


        RecyclerView productView = this.findViewById(R.id.product_view);
        productView.setLayoutManager(new GridLayoutManager(this, 2));

        ProductAdaptor productAdaptor = new ProductAdaptor(this,searchResults, R.layout.product_recycler_view_item, this);
        productView.setAdapter(productAdaptor);
    }

    @Override
    public void onItemClick(int position) {
        Product item = searchResults.get(position);
        MainActivity.updatePopularProducts(item);
        Intent intent = new Intent(ListActivity.this, DetailActivity.class);
        intent.putExtra("product", item);
        startActivity(intent);
    }
}
