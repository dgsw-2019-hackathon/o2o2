package com.example.user.hack;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {
    @GET("value")
    Call<String> a();

    @POST("door")
    Call<Void> b(@Body Request request);
}
