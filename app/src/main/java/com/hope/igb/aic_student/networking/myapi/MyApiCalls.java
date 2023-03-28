package com.hope.igb.aic_student.networking.myapi;

import com.hope.igb.aic_student.networking.models.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApiCalls {

    
    @POST("user/create")
    Call<String> createAccount(@Body Student student);

    @GET("user/me")
    Call<Student> getUser();


    @POST("user/token/{email}/{password}")
    Call<String> Login(@Path("email") String email,
                        @Path("password") String password);







}
