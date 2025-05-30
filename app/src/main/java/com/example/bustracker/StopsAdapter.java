package com.example.bustracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
        if(position == 0) {
            holder.rounded.setBackground(ContextCompat.getDrawable(context, R.drawable.round_green));
            holder.topLine.setAlpha(0f);
            holder.bottomLine.setAlpha(1f);
        }
        else if(position==stops.size()-1){
            holder.rounded.setBackground(ContextCompat.getDrawable(context, R.drawable.round_red));
            holder.bottomLine.setAlpha(0f);
            holder.topLine.setAlpha(1f);

        }
        else{
            holder.rounded.setBackground(ContextCompat.getDrawable(context, R.drawable.round_yellow));
            holder.topLine.setAlpha(1f);
            holder.bottomLine.setAlpha(1f);

        }
    }

    @Override
    public int getItemCount() {
        return stops.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView enterTime;
        ImageView rounded,topLine,bottomLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            enterTime = itemView.findViewById(R.id.enterTime);
            rounded = itemView.findViewById(R.id.rounded);
            topLine = itemView.findViewById(R.id.topLine);
            bottomLine = itemView.findViewById(R.id.bottomLine);
        }
    }
}
