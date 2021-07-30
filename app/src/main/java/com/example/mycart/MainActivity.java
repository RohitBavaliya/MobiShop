package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    TextView go_register;
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

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_login(email_login.getText().toString(),pass_login.getText().toString());
            }
        });
    }

    private void process_login(String email, String password)
    {
        Call<ResponseModel> call = ApiController.getInstance().getApi().getLogin(email,password);
    }
}