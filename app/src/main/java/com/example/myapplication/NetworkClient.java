package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkClient {

    private static NetworkClient mInstance;
    private Retrofit mRetrofit;

    private NetworkClient(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    static NetworkClient getInstance(String baseUrl) {
        if (mInstance == null) {
            mInstance = new NetworkClient(baseUrl);
        }
        return mInstance;
    }

    ApiService getApi() {
        return mRetrofit.create(ApiService.class);
    }

}
