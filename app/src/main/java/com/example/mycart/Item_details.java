package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Item_details extends AppCompatActivity implements PaymentResultListener {

    TextView product_header,product_price;
    ImageView product_image;

    Button btn_checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        /**
         * Preload payment resources
         */
        Checkout.preload(getApplicationContext());

        product_header = (TextView) findViewById(R.id.product_name);
        product_price = (TextView) findViewById(R.id.product_price);
        product_image = (ImageView) findViewById(R.id.product_image);

        btn_checkout = (Button) findViewById(R.id.btn_checkout);

        product_header.setText(getIntent().getStringExtra("product_header"));
        product_price.setText(getIntent().getStringExtra("product_price"));
        String url = "http://192.168.35.146/ecomapi/images/"+getIntent().getStringExtra("product_image");
        Glide.with(Item_details.this).load(url).into(product_image);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();

            }
        });
    }

    public void startPayment() {
        int price  = Integer.parseInt(product_price.getText().toString());
        /**   * Instantiate Checkout   */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_ouUIvkdOYMTbD2");


        /**   * Set your logo here   */
        checkout.setImage(R.drawable.logo);
        /**   * Reference to current activity   */
        final Activity activity = this;
        /**   * Pass your payment options to the Razorpay Checkout as a JSONObject   */
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Rohit Bavaliya");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb"); //from response of step 3.

            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", price*100); // price*100

            options.put("prefill.email", "rohit13bavaliya@gmail.com");
            options.put("prefill.contact","6353481233");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);
            checkout.open(activity, options);
        }
        catch(Exception e)
        {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getApplicationContext(), "Payment successfull", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(), "Payment failed", Toast.LENGTH_SHORT).show();
    }


}