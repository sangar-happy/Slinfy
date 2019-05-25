package com.example.challenge1.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.challenge1.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardRecyclerViewHolder> {

    public DashboardRecyclerAdapter() {
    }

    public class DashboardRecyclerViewHolder extends RecyclerView.ViewHolder {
        public DashboardRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public DashboardRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout, parent, false);
        return new DashboardRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}

