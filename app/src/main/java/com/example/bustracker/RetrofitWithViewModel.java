//package com.example.bustracker;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//// Import necessary packages
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RetrofitWithViewModel extends AppCompatActivity {
//    private TextView textView;
//    ArrayList<String> array = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_retrofit_api);
//
//        PostViewModel postViewModel = new ViewModelProvider(RetrofitWithViewModel.this).get(PostViewModel.class);
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(RetrofitWithViewModel.this));
//
//        MyAdapter myAdapter = new MyAdapter(RetrofitWithViewModel.this,array);
//
//        recyclerView.setAdapter(myAdapter);
//
//
//        postViewModel.getPost().observe(RetrofitWithViewModel.this, new Observer<List<Post>>() {
//            @Override
//            public void onChanged(List<Post> posts) {
//                int n = posts.size();
//                for(int i=0;i<n;i++){
//                    array.add(posts.get(i).getId()+"");
//                }
//                Toast.makeText(RetrofitWithViewModel.this,"Changes"+n,Toast.LENGTH_SHORT).show();
//                myAdapter.notifyDataSetChanged();
//            }
//        });
//    }
//}
//
