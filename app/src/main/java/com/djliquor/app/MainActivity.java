package com.djliquor.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SearchView searchView = findViewById(R.id.search_view);
        LinearLayout logo = findViewById(R.id.logo_linear_layout);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked Search!", Toast.LENGTH_SHORT).show();
                logo.setVisibility(searchView.INVISIBLE);
                searchView.setMaxWidth(9999999);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(MainActivity.this, "Closed Search!", Toast.LENGTH_SHORT).show();
                logo.setVisibility(searchView.VISIBLE);
                return false;
            }
        });

    }
}