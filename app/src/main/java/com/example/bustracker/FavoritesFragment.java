package com.example.bustracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    public FavoritesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
        TextView noFavourites = v.findViewById(R.id.noFavourite);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Favourites", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("Array",null);

        Type type = new TypeToken<ArrayList<data>>() {}.getType();
        ArrayList<data> arrayList;
        if(json != null){

            noFavourites.setAlpha(0f);
            arrayList = gson.fromJson(json,type);
        }else{
            Log.d("Error","Json is null");
            arrayList = new ArrayList<>();
        }
        if(arrayList.isEmpty()) noFavourites.setAlpha(1f);

        BusAdapter_for_Search busAdapterForSearch = new BusAdapter_for_Search(getContext(),arrayList,false);
        recyclerView.setAdapter(busAdapterForSearch);
        busAdapterForSearch.notifyDataSetChanged();

        return v;
    }
}