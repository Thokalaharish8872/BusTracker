package com.example.bustracker;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDataToRetrofit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bus_driver_log_in);


        PostRetrofit post = RetrofitInstance.getRetrofitInstance().create(PostRetrofit.class);

        User user = new User("Harish","23ag1a6959@gmail.com","887243702");

        Call<User> call = post.RegisterUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PostDataToRetrofit.this,response.body().getName().toString()+" "+response.code(),Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(PostDataToRetrofit.this,response.code()+" Error",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(PostDataToRetrofit.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}