package com.example.mycart;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiSet {
    @FormUrlEncoded
    @POST("user_signup.php")
    Call<ResponseModel> getRegister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("address") String address
    );

    @POST("login.php")
    Call<ResponseModel> getLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
