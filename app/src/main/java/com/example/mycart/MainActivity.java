package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mycart.models.Model_Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView go_register, login_result, login_header;
    EditText email_login, pass_login;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        email_login = (EditText) findViewById(R.id.email_login);
        pass_login = (EditText) findViewById(R.id.pass_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        login_header = (TextView) findViewById(R.id.login_header);
        login_result = (TextView) findViewById(R.id.login_result);

        // check user already login or not
        checkUserExist();

        go_register = (TextView) findViewById(R.id.go_register);
        go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_login(email_login.getText().toString(),pass_login.getText().toString());
            }
        });
    }

    // check user already login or not
    private void checkUserExist() {
        SharedPreferences sharedPreferences = getSharedPreferences("details_login",MODE_PRIVATE);
        if(sharedPreferences.contains("email"))
            startActivity(new Intent(MainActivity.this,Dashboard.class));
    }

    private void process_login(String email, String password)
    {

        Call<Model_Login> call = ApiController.getInstance().getApi()
                .getLogin(email,password);

        call.enqueue(new Callback<Model_Login>() {
            @Override
            public void onResponse(Call<Model_Login> call, Response<Model_Login> response)
            {
                Model_Login obj = response.body();
                String result = obj.getMessage().trim();
                if(result.equals("Exist"))
                {
                    login_header.setVisibility(View.INVISIBLE);

                    SharedPreferences sharedPreferences = getSharedPreferences("details_login",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email",email);
                    editor.putString("password",password);
                    editor.commit();
                    editor.apply();

                    login_result.setText("Login Successfull");
                    email_login.setText("");
                    pass_login.setText("");
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    finish();

                }

                if(result.equals("NotExist"))
                {
                    login_result.setText("User Details Not Found!");
                    email_login.setText("");
                    pass_login.setText("");
                }
            }

            @Override
            public void onFailure(Call<Model_Login> call, Throwable t)
            {
                login_result.setText("Login Failed!!");
                login_result.setTextColor(Color.RED);
                email_login.setText("");
                pass_login.setText("");
            }
        });
    }
}