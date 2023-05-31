package com.djliquor.app.activities;

import static com.djliquor.app.providers.ProductProvider.generateData;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SearchView;

import android.view.View;
import android.widget.GridView;

import com.djliquor.app.R;
import com.djliquor.app.adaptors.CategoryAdaptor;
import com.djliquor.app.adaptors.ProductAdaptor;
import com.djliquor.app.databinding.ActivityMainBinding;
import com.djliquor.app.intefaces.IProductView;
import com.djliquor.app.models.Category;
import com.djliquor.app.models.Product;
import com.djliquor.app.providers.CategoryProvider;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IProductView {
    private ActivityMainBinding binding;
    private ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        products = new ArrayList<Product>(generateData().subList(0,3));

        SearchView searchView = (SearchView) this.findViewById(R.id.search_view);
        ImageView backButton = (ImageView) this.findViewById(R.id.back_button);
        GridView categoryView = (GridView) this.findViewById(R.id.categoryView);
        RecyclerView productView = this.findViewById(R.id.popular_view);
        productView.setLayoutManager(new GridLayoutManager(this, 3));
        ProductAdaptor productAdaptor = new ProductAdaptor(this,products, R.layout.popular_recycler_view_item, this);
        productView.setAdapter(productAdaptor);

        List<Category> categories = CategoryProvider.getCategories();
        CategoryAdaptor categoryAdapter = new CategoryAdaptor(this, R.layout.category_grid_view_item, categories);

        categoryView.setAdapter(categoryAdapter);

        backButton.setVisibility(View.INVISIBLE);
        backButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              searchView.setQuery("", false);
              searchView.setIconified(true);
          }
      });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton.setVisibility(View.VISIBLE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                backButton.setVisibility(View.INVISIBLE);
                return false;
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("search", query);
                startActivity(intent);
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        categoryView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                Category category = categoryAdapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("category", category.getType());
                startActivity(intent);
            }
        });

        // Dialog to confirm user age
        new AlertDialog.Builder(this)
                .setTitle("Confirm Age")
                .setMessage("Are you over 18?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAndRemoveTask();
                    }
                })
                .show();
    }

    @Override
    public void onItemClick(int position) {
        Product item = products.get(position);

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("product", item);
        startActivity(intent);
    }
}