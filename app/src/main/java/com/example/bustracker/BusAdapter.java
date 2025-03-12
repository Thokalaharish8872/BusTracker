package com.example.bustracker;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolder> {
    ArrayList<data> arr;
    Context context;
    int lastPosition = -1;
    public BusAdapter(Context context, ArrayList<data> arr){
        this.context = context;
        this.arr = arr;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.eachbus,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.Start.setText(arr.get(position).Start);
            holder.BusNumber.setText(arr.get(position).BusNumber);
            holder.End.setText("Ace Engineering College");
            int y = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, MapsActivity.class);
                    i.putExtra("NumberPlate",arr.get(y).NumberPlate);
                    i.putExtra("stops",arr.get(y).stops);
                    i.putExtra("Start",arr.get(y).Start);
                    i.putExtra("StartTime",arr.get(y).StartTime);
                    i.putExtra("EndTime",arr.get(y).EndTime);
                    i.putExtra("BusNumber",arr.get(y).BusNumber);
                    i.putExtra("ArrivalTime",arr.get(y).ArrivingTime);

                    context.startActivity(i);
                }
            });
            setAnimation(holder.itemView,position);

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView BusNumber,Start,End;
        CardView cardView;

        public ViewHolder(View v) {
            super(v);
            this.Start = v.findViewById(R.id.Start);
            this.BusNumber = v.findViewById(R.id.BusNumber);
            this.End = v.findViewById(R.id.End);
            this.cardView = v.findViewById(R.id.CardView);
        }
    }
    private  void setAnimation(View v,int position){
        if(position>lastPosition) {
            Animation anim = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            v.setAnimation(anim);
            lastPosition = position;
        }
    }
}
