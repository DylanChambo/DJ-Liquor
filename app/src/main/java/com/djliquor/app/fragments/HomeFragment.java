package com.djliquor.app.fragments;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.djliquor.app.R;
import com.djliquor.app.adaptors.CategoryAdaptor;
import com.djliquor.app.databinding.FragmentHomeBinding;
import com.djliquor.app.models.Category;
import com.djliquor.app.providers.CategoryProvider;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        List<Category> categories = CategoryProvider.getCategories();
        CategoryAdaptor categoryAdapter = new CategoryAdaptor(getContext(), R.layout.category_list_view_item, categories);

        ListView categoryView = (ListView) view.findViewById(R.id.categoryView);
        categoryView.setAdapter(categoryAdapter);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
