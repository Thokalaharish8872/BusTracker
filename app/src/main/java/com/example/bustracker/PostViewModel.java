//package com.example.bustracker;
//
//import android.widget.ListPopupWindow;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.ViewModel;
//
//import java.util.List;
//
//public class PostViewModel extends ViewModel {
//    private LiveData<List<Post>> posts;
//
//    PostViewModel(){
//        posts = new PostRepository().fetchPosts();
//    }
//
//    LiveData<List<Post>> getPost(){
//        return posts;
//    }
//}
