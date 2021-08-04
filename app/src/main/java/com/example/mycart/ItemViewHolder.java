package com.example.mycart;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView header;
    ImageView itemImage;
    TextView price;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        header = itemView.findViewById(R.id.itemName);
        itemImage = itemView.findViewById(R.id.itemImage);
        price = itemView.findViewById(R.id.itemPrice);
    }
}
