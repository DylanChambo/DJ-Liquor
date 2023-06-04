package com.djliquor.app.adaptors;

import android.content.Context;
import android.util.Pair;
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
import com.djliquor.app.providers.ProductProvider;

import java.util.List;
import java.util.Map;

public class CartAdaptor extends ArrayAdapter {
    int mLayoutID;
    List<Pair<Product, Integer>> mProducts;
    Context mContext;
    public CartAdaptor(@NonNull Context context, int resource, @NonNull List<Pair<Product, Integer>> objects)
    {
        super(context, resource, objects);
        mLayoutID = resource;
        mContext = context;
        mProducts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View currentCartViewItem = convertView;

        if (currentCartViewItem == null)
        {
            currentCartViewItem = LayoutInflater.from(getContext())
                    .inflate(mLayoutID, parent, false);
        }

        Pair<Product, Integer> currentCartItem = mProducts.get(position);
        Product currentProduct = currentCartItem.first;
        int currentCount = currentCartItem.second;

        ImageView imageView = currentCartViewItem.findViewById(R.id.product_img);
        int i = mContext.getResources().getIdentifier(currentProduct.getImageAddress() + '0',
                "drawable", mContext.getPackageName());
        imageView.setImageResource(i);

        TextView nameView = currentCartViewItem.findViewById(R.id.product_name);
        nameView.setText(currentProduct.getName());

        TextView priceView = currentCartViewItem.findViewById(R.id.product_price);
        priceView.setText("$" + currentProduct.getCost());

        TextView countView = currentCartViewItem.findViewById(R.id.product_quantity);
        countView.setText("" + currentCount);

        return currentCartViewItem;
    }

    public Pair<Product, Integer> getItem(int position){
        return mProducts.get(position);
    }
}
