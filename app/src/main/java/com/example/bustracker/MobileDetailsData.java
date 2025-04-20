package com.example.bustracker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

public class MobileDetailsData {

    static final String URL = "https://jsonplaceholder.typicode.com/posts";

    static JSONArray response = null;
    static MobileDetailsData instance = null;
    static JsonArrayRequest request;
    static String data;

    private MobileDetailsData(Context context) {

        request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response1) {
                response = response1;
                Toast.makeText(context,"Data is Loaded",Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error Fetching Data"+error, Toast.LENGTH_SHORT).show();
                Log.d("Error",error.toString());
            }
        });

        MySingleton.getInstance(context).add(request);
    }

    static MobileDetailsData getInstance(Context context) {
        if (instance == null) {
            instance = new MobileDetailsData(context);
            Toast.makeText(context, "Instance Created", Toast.LENGTH_SHORT).show();
        }
        return instance;
    }

    public String getID(Context context, String name) {
        if (response == null) {
            Toast.makeText(context, "Data not loaded yet. Please wait.", Toast.LENGTH_SHORT).show();
            return null;
        }

        try {
            for (int i = 0; i < response.length(); i++) {
                if (response.getJSONObject(i).getString("userId").equals(name)) {
                    String id = response.getJSONObject(i).getString("id");
                    Toast.makeText(context, "Phone ID: " + id, Toast.LENGTH_SHORT).show();
                    return id;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(context, "Phone not found", Toast.LENGTH_SHORT).show();
        return null;
    }
}
