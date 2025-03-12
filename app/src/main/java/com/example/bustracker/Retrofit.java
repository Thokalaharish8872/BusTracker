package com.example.bustracker;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.common.internal.Asserts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit extends AppCompatActivity {

    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_retrofit);


//        TextView textView = findViewById(R.id.textview30);

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Retrofit.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<DataModel> call = apiService.fetchData();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                if(response.isSuccessful()){
                    DataModel dataModel = response.body();
                    arrayList.add(dataModel.getUserId()+"");
                    arrayList.add(dataModel.getId()+"");
                    arrayList.add(dataModel.getTitle());
                    arrayList.add(dataModel.getBody());

                    adapter.notifyDataSetChanged();



                    Log.d("error",dataModel.getBody()+"");

                }
            }
            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d("error","2nd");
            }
        });



    }
}