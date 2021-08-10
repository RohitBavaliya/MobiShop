package com.example.mycart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycart.models.Model_item_data;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Dashboard extends RecyclerView.Adapter<ItemViewHolder> {
    List<Model_item_data> product_data;
    List<Model_item_data> backup_data;
    Context context;

    public Adapter_Dashboard(List<Model_item_data> product_data,Context context) {
        this.product_data = product_data;
        this.context = context;

        backup_data = new ArrayList<>(product_data);  // backup copy of original ArrayList

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_design,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.header.setText(product_data.get(position).getHeader());
        holder.price.setText(product_data.get(position).getPrice());
        Glide.with(holder.header.getContext())
                .load("http://192.168.93.146/ecomapi/images/"
                        +product_data.get(position).getImage()).into(holder.itemImage);
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Item_details.class);
                intent.putExtra("product_header",product_data.get(position).getHeader());
                intent.putExtra("product_image",product_data.get(position).getImage());
                intent.putExtra("product_price",product_data.get(position).getPrice());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_data.size();
    }


    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override  // background Thread
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<Model_item_data> filterData = new ArrayList<>();
            if (keyword.toString().isEmpty())
            {
                filterData.addAll(backup_data);
            }
            else {
                for (Model_item_data obj:backup_data)
                {
                    if (obj.getHeader().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                    {
                        filterData.add(obj);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterData;
            return results;
        }

        @Override  // main UI Thread
        protected void publishResults(CharSequence keyword, FilterResults results) {
            // clear ArrayList
            product_data.clear();
            // add all search values
            product_data.addAll((ArrayList<Model_item_data>)results.values);
            notifyDataSetChanged();
        }
    };
}
