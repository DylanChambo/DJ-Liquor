package com.djliquor.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.djliquor.app.R;
import com.djliquor.app.databinding.ActivityListBinding;
import com.djliquor.app.databinding.ActivityMainBinding;

public class ListActivity extends AppCompatActivity {

    private ActivityListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SearchView searchView = (SearchView) this.findViewById(R.id.search_view);
        TextView tv = this.findViewById(R.id.search_text);
        ImageView backButton = (ImageView) this.findViewById(R.id.back_button);

        String search = getIntent().getExtras().getString("search");

        tv.setText("RESULTS FOR `" + search.toUpperCase() + "`");
        searchView.setQuery(search, false);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery(search, false);
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
}
