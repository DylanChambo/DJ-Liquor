package com.djliquor.app.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.djliquor.app.databinding.ActivityMainBinding;
import com.djliquor.app.models.Category;
import com.djliquor.app.providers.CategoryProvider;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SearchView searchView = (SearchView) this.findViewById(R.id.search_view);
        ImageView backButton = (ImageView) this.findViewById(R.id.back_button);
        GridView categoryView = (GridView) this.findViewById(R.id.categoryView);

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
}