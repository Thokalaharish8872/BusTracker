package com.example.bustracker;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton instance = null;
    private RequestQueue queue = null;
    private final Context context;

    private MySingleton(Context context){
        this.context = context;
        queue = getRequestQueue();
    }

   public static MySingleton getInstance(Context context){
        if(instance == null){
            instance = new MySingleton(context);
        }
        return instance;
    }
    RequestQueue getRequestQueue() {
        if(queue == null){
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }
    <T> void add(Request<T> request){
        queue.add(request);
    }
}
