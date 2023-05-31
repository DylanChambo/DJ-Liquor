package com.djliquor.app.fragments;

import static com.djliquor.app.providers.ProductProvider.generateData;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.djliquor.app.R;
import com.djliquor.app.adaptors.ProductAdaptor;
import com.djliquor.app.databinding.FragmentSearchBinding;
import com.djliquor.app.models.Product;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        TextView tv = view.findViewById(R.id.search_text);



        tv.setText("RESULTS FOR `" + getArguments().getString("search") + "`");

        performSearch(getArguments().getString("search"), view);

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

    public void performSearch(String query, View view) {
        ArrayList<Product> searchResults = new ArrayList<Product>();
        ArrayList<Product> data = new ArrayList<Product>();
        data = generateData();

        for (int i = 0; i < 5; i++) {
            if (query.toLowerCase().equals(data.get(i).getName().toLowerCase())) {
                searchResults.add(data.get(i));
            }
        }

        GridView recyclerView = view.findViewById(R.id.search_test_View);
        ProductAdaptor productAdaptor = new ProductAdaptor(getContext(), R.layout.category_list_view_item,searchResults);
        recyclerView.setAdapter(productAdaptor);


    }

}
