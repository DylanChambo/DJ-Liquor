package com.djliquor.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView backIcon = findViewById(R.id.back_icon);
        ImageView searchIcon = findViewById(R.id.search_icon);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked Back!", Toast.LENGTH_SHORT).show();
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked Search!", Toast.LENGTH_SHORT).show();
            }
        });

        toolbarTitle.setText("DJ Liquor");

    }
}