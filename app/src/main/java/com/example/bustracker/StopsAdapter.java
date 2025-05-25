package com.example.bustracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {
    ArrayList<String> stops;
    Context context;
    public StopsAdapter(ArrayList<String> stops, Context context){
        this.stops = stops;
        this.context = context;
    }
    @NonNull
    @Override
    public StopsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.each_stop,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StopsAdapter.ViewHolder holder, int position) {
        holder.enterTime.setText(stops.get(position));
        if (position == 1){
            Toast.makeText(context,stops.get(position),Toast.LENGTH_SHORT).show();
            holder.rounded.setBackground(context.getDrawable(R.drawable.round_red));
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView enterTime;
        ImageView rounded;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            enterTime = itemView.findViewById(R.id.enterTime);
            rounded = itemView.findViewById(R.id.rounded);
        }
    }
}
