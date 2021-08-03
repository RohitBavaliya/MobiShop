package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView go_register, login_result;
    EditText email_login, pass_login;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        go_register = (TextView) findViewById(R.id.go_register);
        go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });

        email_login = (EditText) findViewById(R.id.email_login);
        pass_login = (EditText) findViewById(R.id.pass_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        login_result = (TextView) findViewById(R.id.login_result);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_login(email_login.getText().toString(),pass_login.getText().toString());
            }
        });
    }

    private void process_login(String email, String password)
    {

        Call<ResponseModel> call = ApiController.getInstance().getApi()
                .getLogin(email,password);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response)
            {
                ResponseModel obj = response.body();
                String result = obj.getMessage().trim();
                if(result.equals("Exist"))
                {
                    login_result.setText("Login Successfull");
                    email_login.setText("");
                    pass_login.setText("");

                }

                if(result.equals("NotExist"))
                {
                    login_result.setText("User Details Not Found!");
                    email_login.setText("");
                    pass_login.setText("");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t)
            {
                login_result.setText("Login Failed!!");
                login_result.setTextColor(Color.RED);
                email_login.setText("");
                pass_login.setText("");
            }
        });
    }
}