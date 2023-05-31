package com.djliquor.app.activities;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;

import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

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



        List<Category> categories = CategoryProvider.getCategories();
        CategoryAdaptor categoryAdapter = new CategoryAdaptor(this, R.layout.category_list_view_item, categories);

        GridView categoryView = (GridView) this.findViewById(R.id.categoryView);
        categoryView.setAdapter(categoryAdapter);

        ImageView backButton = (ImageView) this.findViewById(R.id.back_button);
        backButton.setVisibility(View.INVISIBLE);

        SearchView searchView = (SearchView) this.findViewById(R.id.search_view);

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