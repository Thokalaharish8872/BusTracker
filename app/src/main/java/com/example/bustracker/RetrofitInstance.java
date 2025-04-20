package com.example.bustracker;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofitInstance;

    static Retrofit getRetrofitInstance(){
         if(retrofitInstance == null){
             retrofitInstance = new Retrofit.Builder()
                     .baseUrl("https://maps.googleapis.com/maps/api/")
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
         return retrofitInstance;
     }
}
