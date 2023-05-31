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
import androidx.recyclerview.widget.RecyclerView;

import com.djliquor.app.intefaces.IProductView;
import com.djliquor.app.R;
import com.djliquor.app.models.Category;
import com.djliquor.app.models.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ProductViewHolder> {
    private final IProductView iProductView;
    private final int noImage;
    ArrayList<Product> mProducts;
    Context mContext;

    int layoutID;

    public ProductAdaptor(Context context, ArrayList<Product> products, int resource,IProductView iProductView) {
        mContext = context;
        mProducts = products;
        noImage = mContext.getResources().getIdentifier("baseline_image_24",
                "drawable", mContext.getPackageName());
        this.iProductView = iProductView;
        layoutID = resource;
    }
    @NonNull
    @Override
    public ProductAdaptor.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layoutID, parent, false);
        return new ProductAdaptor.ProductViewHolder(view, iProductView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdaptor.ProductViewHolder holder, int position) {
        holder.textView.setText(mProducts.get(position).getName());
        int i = mContext.getResources().getIdentifier(mProducts.get(position).getImageAddress(),
                "drawable", mContext.getPackageName());
        if (i == 0){
            i = noImage;
        }
        holder.imageView.setImageResource(i);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ProductViewHolder(@NonNull View itemView, IProductView iProductView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.category_img_view);
            textView = itemView.findViewById(R.id.category_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (iProductView != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            iProductView.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
