package com.djliquor.app.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.djliquor.app.databinding.ActivityListBinding;
import com.djliquor.app.databinding.ActivityMainBinding;

public class ListActivity extends AppCompatActivity {

    private ActivityListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
