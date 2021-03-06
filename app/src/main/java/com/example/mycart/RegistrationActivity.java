package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mycart.models.Model_Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText name_reg, email_reg, mobile_reg, password_reg, address_reg;
    Button btn_reg;
    TextView signup_result, signup_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        signup_result = (TextView) findViewById(R.id.signup_result);
        signup_header = (TextView) findViewById(R.id.signup_header);
        name_reg = (EditText) findViewById(R.id.name_reg);
        email_reg = (EditText) findViewById(R.id.email_reg);
        mobile_reg = (EditText) findViewById(R.id.mobile_reg);
        password_reg = (EditText) findViewById(R.id.password_reg);
        address_reg = (EditText) findViewById(R.id.address_reg);

        btn_reg = (Button) findViewById(R.id.btn_reg);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_reg(name_reg.getText().toString(),
                        email_reg.getText().toString(),
                        mobile_reg.getText().toString(),
                        password_reg.getText().toString(),
                        address_reg.getText().toString());
            }
        });


    }

    private void process_reg(String name, String email, String mobile, String password, String address)
    {

        Call<Model_Register> call = ApiController.getInstance().getApi()
                                    .getRegister(name,email,password,mobile,address);

        call.enqueue(new Callback<Model_Register>() {
            @Override
            public void onResponse(Call<Model_Register> call, Response<Model_Register> response)
            {
                Model_Register obj = response.body();
                String result = obj.getMessage().trim();
                if(result.equals("inserted"))
                {
                    signup_header.setVisibility(View.INVISIBLE);
                    signup_result.setText("Registration Successfull");
                    name_reg.setText("");
                    email_reg.setText("");
                    mobile_reg.setText("");
                    password_reg.setText("");
                    address_reg.setText("");
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }

                if(result.equals("exists"))
                {
                    signup_result.setText("You are already registered");
                    signup_result.setTextColor(Color.RED);
                    name_reg.setText("");
                    email_reg.setText("");
                    mobile_reg.setText("");
                    password_reg.setText("");
                    address_reg.setText("");
                }
            }

            @Override
            public void onFailure(Call<Model_Register> call, Throwable t)
            {
                signup_result.setText("Registration Failed!!");
                signup_result.setTextColor(Color.RED);
                name_reg.setText("");
                email_reg.setText("");
                mobile_reg.setText("");
                password_reg.setText("");
                address_reg.setText("");
            }
        });
    }
}