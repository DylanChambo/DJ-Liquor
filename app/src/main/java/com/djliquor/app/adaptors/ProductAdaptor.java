package com.djliquor.app.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.djliquor.app.intefaces.IProductView;
import com.djliquor.app.R;
import com.djliquor.app.models.Product;
import java.util.ArrayList;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ProductViewHolder> {
    private final IProductView iProductView;
    ArrayList<Product> mProducts;
    Context mContext;
    public ProductAdaptor(Context context, ArrayList<Product> products, IProductView iProductView) {
        mContext = context;
        mProducts = products;
        this.iProductView = iProductView;
    }
    @NonNull
    @Override
    public ProductAdaptor.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.product_recycler_view_item, parent, false);
        return new ProductAdaptor.ProductViewHolder(view, iProductView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdaptor.ProductViewHolder holder, int position) {
        holder.textView.setText(mProducts.get(position).getName());
        int i = mContext.getResources().getIdentifier(mProducts.get(position).getImageAddress(),
                "drawable", mContext.getPackageName());
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
