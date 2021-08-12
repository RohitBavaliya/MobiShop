package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Item_details extends AppCompatActivity {

    TextView product_header,product_price;
    ImageView product_image;

    Button btn_checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        product_header = (TextView) findViewById(R.id.product_name);
        product_price = (TextView) findViewById(R.id.product_price);
        product_image = (ImageView) findViewById(R.id.product_image);

        btn_checkout = (Button) findViewById(R.id.btn_checkout);

        product_header.setText(getIntent().getStringExtra("product_header"));
        product_price.setText(getIntent().getStringExtra("product_price"));
        String url = "http://192.168.93.146/ecomapi/images/"+getIntent().getStringExtra("product_image");
        Glide.with(Item_details.this).load(url).into(product_image);
    }
}