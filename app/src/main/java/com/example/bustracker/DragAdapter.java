package com.example.bustracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class DragAdapter extends RecyclerView.Adapter<DragAdapter.ViewHolder> {
        private List<String> itemList;
        private Context context;

        public DragAdapter(List<String> itemList, Context context) {
            this.itemList = itemList;
            this.context = context;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }

        @Override
        public DragAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DragAdapter.ViewHolder holder, int position) {
            holder.textView.setText(itemList.get(position));
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public void onItemMove(int fromPosition, int toPosition) {
            Collections.swap(itemList, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
        }

        public void onItemDismiss(int position) {
            itemList.remove(position);
            notifyItemRemoved(position);
        }
    }


