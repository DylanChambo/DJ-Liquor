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

import java.util.List;

public class CategoryAdaptor extends ArrayAdapter {

    int mLayoutID;
    List<Category> mCatagories;
    Context mContext;
    public CategoryAdaptor(@NonNull Context context, int resource, @NonNull List<Category> objects)
    {
        super(context, resource, objects);
        mLayoutID = resource;
        mContext = context;
        mCatagories = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View currentListViewItem = convertView;

        if (currentListViewItem == null)
        {
            currentListViewItem = LayoutInflater.from(getContext())
                    .inflate(mLayoutID, parent, false);
        }

        Category currentCategory = mCatagories.get(position);

        ImageView imageView = currentListViewItem.findViewById(R.id.category_img_view);
        int i = mContext.getResources().getIdentifier(currentCategory.getImageFileName(),
                "drawable", mContext.getPackageName());
        imageView.setImageResource(i);

        TextView textView = currentListViewItem.findViewById(R.id.category_text_view);
        textView.setText(currentCategory.getCategoryName());

        return currentListViewItem;
    }

    public Category getItem(int position){
        return mCatagories.get(position);
    }
}
