package com.example.bustracker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class SearchBus extends RecyclerView.Adapter<SearchBus.ViewHolder> {
    Context context;
    data data;
    SearchBus(Context context, data data){
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public SearchBus.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchBus.ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
