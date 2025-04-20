//package com.example.bustracker;
//
//import android.util.Log;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class PostRepository extends ViewModel {
//
//    private ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
//
//    public LiveData<List<Post>> fetchPosts(){
//        MutableLiveData<List<Post>> post = new MutableLiveData<>();
//
//        apiService.getPosts().enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                Log.d("Fetch","Fetching");
//                post.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                post.setValue(null);
//
//            }
//        });
//        return post;
//    }
//
//}
//
