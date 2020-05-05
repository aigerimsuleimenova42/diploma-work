package com.example.myapplication;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("check")
    Call<JsonObject> sendPing(@Query("pin") String pin);
}
