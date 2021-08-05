package com.example.mycart;

import com.example.mycart.models.Model_Login;
import com.example.mycart.models.Model_Register;
import com.example.mycart.models.Model_item_data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiSet {
    @FormUrlEncoded
    @POST("login.php")
    Call<Model_Login> getLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("user_signup.php")
    Call<Model_Register> getRegister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("address") String address
    );

    @GET("item_data_fetch.php")
    Call<List<Model_item_data>> getFetchData();


}
