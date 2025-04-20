package com.example.bustracker;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostRetrofit {
    @POST("users")
    Call<User> RegisterUser(@Body User user);
}
