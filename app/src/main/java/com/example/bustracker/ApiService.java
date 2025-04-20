package com.example.bustracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("directions/json?")
    Call<DataModel> getRoute(@Query("origin") String origin,@Query("destination") String destination, @Query("mode") String mode,@Query("alternatives") String alternatives,@Query("waypoints") String waypoints,@Query("key") String key);
}
