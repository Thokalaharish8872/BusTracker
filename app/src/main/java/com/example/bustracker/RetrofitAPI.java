//package com.example.bustracker;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ArrayAdapter;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.gson.JsonObject;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
//public class RetrofitAPI extends AppCompatActivity {
//
//    ArrayList<String> arrayList = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_retrofit_api);
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(RetrofitAPI.this));
//
//
//        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
//        MyAdapter adapter = new MyAdapter(RetrofitAPI.this,arrayList);
//
//        recyclerView.setAdapter(adapter);
//
//
//        Call<List<Post>> call = apiService.getPosts();
//
//        call.enqueue(new Callback<List<Post>>() {
//
//
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//
//                Toast.makeText(RetrofitAPI.this,"Fetching again",Toast.LENGTH_SHORT).show();
//                if(response.isSuccessful() && response.body()!=null){
//                    List<Post> list = response.body();
//                    for(Post data: list) {
//                        arrayList.add(data.getId() + "");
//                        adapter.notifyDataSetChanged();
//
//                    }
//                }
//                else{
//                    try {
//                        JSONObject jsonObject = new JSONObject(response.errorBody().toString());
//
//                        String errormsg = jsonObject.getString("message");
//
//                        Toast.makeText(RetrofitAPI.this,errormsg,Toast.LENGTH_SHORT).show();
//                    } catch (JSONException e) {
//                        Log.d("Error : ",e.getMessage());
//                    }
//                    Toast.makeText(RetrofitAPI.this, "Unexpected Error : "+response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//
//                if(t instanceof IOException){
//                        Toast.makeText(RetrofitAPI.this, "No Internet Connection, Please Check Your network", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(RetrofitAPI.this, "Something went wrong "+t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//
//    }
//}