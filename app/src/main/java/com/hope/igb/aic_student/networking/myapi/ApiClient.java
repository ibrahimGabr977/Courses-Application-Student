package com.hope.igb.aic_student.networking.myapi;

import com.hope.igb.aic_student.common.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Retrofit retrofit;

    private Retrofit getRetrofit(){
        if (retrofit == null)
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        return retrofit;
    }


    public MyApiCalls getApiCalls(){
        return getRetrofit().create(MyApiCalls.class);
    }


}
