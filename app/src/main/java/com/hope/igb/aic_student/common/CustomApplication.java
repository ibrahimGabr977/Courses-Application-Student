package com.hope.igb.aic_student.common;

import android.app.Application;

import com.hope.igb.aic_student.networking.myapi.ApiClient;

public class CustomApplication extends Application {

    private ApiClient serverApi;


    @Override
    public void onCreate() {
        super.onCreate();
        serverApi = new ApiClient();
    }


    public ApiClient getServerApi() {
        return serverApi;
    }
}
