package com.example.mycart;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    final static String url = "http://192.168.93.146/ecomapi/";
    private static ApiController controller;
    private static Retrofit retrofit;

    ApiController()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiController getInstance()
    {
        if (controller==null)
            controller = new ApiController();
        return controller;
    }

    // call api
    ApiSet getApi()
    {
        return retrofit.create(ApiSet.class);
    }
}
