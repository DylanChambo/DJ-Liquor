package com.djliquor.app.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.djliquor.app.R;
import com.djliquor.app.models.Category;
import com.djliquor.app.models.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductAdaptor extends ArrayAdapter {

    int mLayoutID;
    ArrayList<Product> mProducts;
    Context mContext;

    public ProductAdaptor(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        mLayoutID = resource;
        mContext = context;
        mProducts = products;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentView = convertView;

        if (currentView == null)
        {
            currentView = LayoutInflater.from(getContext())
                    .inflate(mLayoutID, parent, false);
        }

        Product currentProduct = mProducts.get(position);

        ImageView imageView = currentView.findViewById(R.id.category_img_view);
        int i = mContext.getResources().getIdentifier(currentProduct.getImageAddress(),
                "drawable", mContext.getPackageName());
        imageView.setImageResource(i);

        TextView textView = currentView.findViewById(R.id.category_text_view);
        textView.setText(currentProduct.getName());

        return currentView;

    }

}
