package com.example.bustracker;

import android.annotation.SuppressLint;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class AwardsFragment extends Fragment {


    private LinearLayout stopList;
    private ScrollView scrollView;
    private float yDelta;

    public AwardsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_awards, container, false);

        stopList = v.findViewById(R.id.stop_list);
        scrollView = v.findViewById(R.id.draggable_scroll);


        return v;
    }

    private void addStop(String time, String title, String address) {
        View stopItem = getLayoutInflater().inflate(R.layout.item_stop, null);

        TextView timeText = stopItem.findViewById(R.id.stop_time);
        TextView titleText = stopItem.findViewById(R.id.stop_title);
        TextView addressText = stopItem.findViewById(R.id.stop_address);

        timeText.setText(time);
        titleText.setText(title);
        addressText.setText(address);

        stopList.addView(stopItem);
    }

    private void enableDrag() {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            float dY;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dY = view.getY() - event.getRawY();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        view.animate()
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}