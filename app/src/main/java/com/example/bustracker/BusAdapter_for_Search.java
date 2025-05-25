package com.example.bustracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BusAdapter_for_Search extends RecyclerView.Adapter<BusAdapter_for_Search.ViewHolder> {
    Context context;
    ArrayList<data> array;
    boolean flag;
    BusAdapter_for_Search(Context context, ArrayList<data> array,boolean flag){
        this.context = context;
        this.array = array;
        this.flag = flag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.each_bus_for_search,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int hour=1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             LocalTime time = LocalTime.now();
             hour = time.getHour();
        }

        data arrayListPos = array.get(position);

        if(hour<12) {
            holder.Start.setText(arrayListPos.Start);
            holder.End.setText("Ace Engineering College");
            holder.StartTime.setText(arrayListPos.StartTime);
            holder.EndTime.setText(arrayListPos.EndTime);
            holder.ArrivingTime.setText(arrayListPos.ArrivingTime);
            holder.BusNumber.setText(arrayListPos.BusNumber);
            holder.NumberPlate.setText(arrayListPos.NumberPlate);
            holder.stops.setText("stops : " + arrayListPos.stops);
            holder.TimeHeading.setText("Arriving In");
        }
        else{
            holder.End.setText(arrayListPos.Start);
            holder.Start.setText("Ace Engineering Collge");
            holder.StartTime.setText("4:50");
            holder.EndTime.setText("7:25");
            holder.ArrivingTime.setText(arrayListPos.ArrivingTime);
            holder.BusNumber.setText(arrayListPos.BusNumber);
            holder.NumberPlate.setText(arrayListPos.NumberPlate);
            holder.stops.setText("stops : " + arrayListPos.stops);
            holder.TimeHeading.setText("Drop of In");
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int clickedItem = holder.getAdapterPosition();

                PopupMenu popupMenu = new PopupMenu(context,view);

                SharedPreferences sharedPreferences = context.getSharedPreferences("Favourites",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Gson gson = new Gson();
                String[] json = {sharedPreferences.getString("Array", null)};
                Type type = new TypeToken<ArrayList<data>>() {}.getType();

                if(flag){
                    popupMenu.getMenu().add("Set As Favourite");

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if(menuItem.getTitle().equals("Set As Favourite")){

                                ArrayList<data> arrayList = null;
                                if(json[0] != null) {
                                    arrayList= gson.fromJson(json[0], type);

                                    boolean flag = false;
                                    for(int i=0;i<arrayList.size();i++) {
                                        if(arrayList.get(i).BusNumber.equals(array.get(clickedItem).BusNumber)){
                                            flag = true;
                                            Toast.makeText(context,"Bus is Already in Your Favourites",Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }
                                    if(!flag){
                                        arrayList.add(array.get(clickedItem));
                                        Toast.makeText(context,"Bus Successufully Added to Favourites", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    arrayList = new ArrayList<>();
                                    arrayList.add(array.get(clickedItem));
                                    Toast.makeText(context,"Bus Successufully Added to Favourites", Toast.LENGTH_SHORT).show();
                                }

                                json[0] = gson.toJson(arrayList, type);
                                editor.putString("Array", json[0]);
                                editor.apply();
                            }
                            return true;
                        }
                    });
                }
                else{
                    popupMenu.getMenu().add("Remove From Favourites");

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if(menuItem.getTitle().equals("Remove From Favourites")) {


                                ArrayList<data> arrayList = gson.fromJson(json[0], type);

                                if (!arrayList.isEmpty()) {

                                    SharedPreferences sharedPreferences1 = context.getSharedPreferences("Favourites", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor1 = sharedPreferences1.edit();

                                    arrayList.remove(clickedItem);
                                    json[0] = gson.toJson(arrayList, type);
                                    editor1.putString("Array", json[0]);
                                    editor1.apply();

                                    notifyItemRemoved(clickedItem);
                                    Toast.makeText(context, "Remove Success", Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    Toast.makeText(context,"Item Already Removed, Please refresh the page",Toast.LENGTH_SHORT).show();
                                }
                            }
                            return true;
                        }
                    });
                }
                popupMenu.show();
                return true;
            }
        });

        int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String waypoints[] = new String[2];
//
                if(array.get(pos).BusNumber.equals("08")){
                    waypoints[0]= "via:17.35529372435894, 78.55642408980903";
                }
//

//                if(array.get(pos).BusNumber.equals("22")) {
                    Intent i = new Intent(context, ActivityForBus.class);
                    i.putExtra("NumberPlate", array.get(pos).NumberPlate);
                    i.putExtra("stops", array.get(pos).stops);
                    i.putExtra("Start", array.get(pos).Start);
                    i.putExtra("StartTime", array.get(pos).StartTime);
                    i.putExtra("EndTime", array.get(pos).EndTime);
                    i.putExtra("BusNumber", array.get(pos).BusNumber);
                    i.putExtra("ArrivingTime", array.get(pos).ArrivingTime);
                    i.putExtra("stLat",array.get(pos).stLat);
                    i.putExtra("stLon",array.get(pos).stLon);
                    i.putExtra("waypoints",waypoints);
                    i.putExtra("points",array.get(pos).points);

                    context.startActivity(i);
//                }
            }
        });
    }

    @Override
    public int getItemCount(){ return array.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Start,StartTime,EndTime,ArrivingTime,BusNumber,NumberPlate,stops,End,TimeHeading;
        ViewHolder(View v){
            super(v);
            Start = v.findViewById(R.id.Start);
            StartTime = v.findViewById(R.id.StartTime);
            EndTime = v.findViewById(R.id.EndTime);
            ArrivingTime = v.findViewById(R.id.ArrivingTime);
            BusNumber = v.findViewById(R.id.BusNumber);
            NumberPlate = v.findViewById(R.id.NumberPlate);
            stops = v.findViewById(R.id.stops);
            End = v.findViewById(R.id.End);
            TimeHeading = v.findViewById(R.id.TimeHeading);
        }
    }
}