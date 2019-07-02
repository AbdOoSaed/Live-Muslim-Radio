package com.example.myapi.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManger {
    private static Retrofit retrofitInstance;

    private static Retrofit getInstance() {
        if (retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(ApiCalls.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }

    public static ApiCalls getApi() {
        ApiCalls apiCalls=getInstance().create(ApiCalls.class);
        return apiCalls;
    }
}
