package com.example.bustracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusAdapter_for_Search extends RecyclerView.Adapter<BusAdapter_for_Search.ViewHolder> {
    Context context;
    ArrayList<data> array;
    BusAdapter_for_Search(Context context, ArrayList<data> array){
        this.context = context;
        this.array = array;
    }


    @NonNull
    @Override
    public BusAdapter_for_Search.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.each_bus_for_search,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BusAdapter_for_Search.ViewHolder holder, int position) {
        holder.Start.setText(array.get(position).Start);
        holder.StartTime.setText(array.get(position).StartTime);
        holder.EndTime.setText(array.get(position).EndTime);
        holder.ArrivingTime.setText(array.get(position).ArrivingTime);
        holder.BusNumber.setText(array.get(position).BusNumber);
        holder.NumberPlate.setText(array.get(position).NumberPlate);
        holder.stops.setText("stops : "+array.get(position).stops);
        int y = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, BusMap.class);
                i.putExtra("NumberPlate",array.get(y).NumberPlate);
                i.putExtra("stops",array.get(y).stops);
                i.putExtra("Start",array.get(y).Start);
                i.putExtra("StartTime",array.get(y).StartTime);
                i.putExtra("EndTime",array.get(y).EndTime);
                i.putExtra("BusNumber",array.get(y).BusNumber);
                i.putExtra("ArrivingTime",array.get(y).ArrivingTime);

                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Start,StartTime,EndTime,ArrivingTime,BusNumber,NumberPlate,stops;
        ViewHolder(View v){
            super(v);
            Start = v.findViewById(R.id.Start);
            StartTime = v.findViewById(R.id.StartTime);
            EndTime = v.findViewById(R.id.EndTime);
            ArrivingTime = v.findViewById(R.id.ArrivingTime);
            BusNumber = v.findViewById(R.id.BusNumber);
            NumberPlate = v.findViewById(R.id.NumberPlate);
            stops = v.findViewById(R.id.stops);
        }
    }
}
