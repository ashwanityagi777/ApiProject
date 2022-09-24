package com.example.apiproject;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageAPI {

    @GET("random")
    Call<JsonObject> getImage();


}
