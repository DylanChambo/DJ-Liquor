package com.djliquor.app.activities;

import static com.djliquor.app.providers.ProductProvider.generateData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.djliquor.app.R;
import com.djliquor.app.adaptors.ProductAdaptor;
import com.djliquor.app.databinding.ActivityListBinding;
import com.djliquor.app.databinding.ActivityMainBinding;
import com.djliquor.app.models.Category;
import com.djliquor.app.models.Product;
import com.djliquor.app.models.Type;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ActivityListBinding binding;
    private Type category;
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
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setBackButtonTraverse(backButton);
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
        ArrayList<Product> searchResults = generateData();
        if (query != "")
        {
            searchResults.removeIf(product -> !product.getName().toLowerCase().contains(query.toLowerCase()));
        }

        if (category != Type.None)
        {
            searchResults.removeIf(product -> !(product.getCategory() == category));
        }


        GridView recyclerView = this.findViewById(R.id.grid_view);
        ProductAdaptor productAdaptor = new ProductAdaptor(this, R.layout.category_list_view_item,searchResults);
        recyclerView.setAdapter(productAdaptor);
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                Product item = productAdaptor.getItem(position);

                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("product", item);
                startActivity(intent);
            }
        });

    }
}
