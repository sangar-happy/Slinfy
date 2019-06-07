package com.example.challenge1.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challenge1.EventFirebase;
import com.example.challenge1.R;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<EventFirebase> list = new ArrayList<>();

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_layout, parent, false);
        return new EventHolder(eventView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        EventFirebase currentNote = list.get(position);
        holder.category.setText(currentNote.getEventCategory());
        holder.date.setText(currentNote.getEventDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setEventList(List<EventFirebase> events) {
        int size = list.size();
        if(size > 0) {
            list.clear();
            notifyItemRangeRemoved(0, size);
        }
        list.addAll(events);
        notifyDataSetChanged();
    }

    class EventHolder extends RecyclerView.ViewHolder {

        TextView category
                , date;

        public EventHolder(@NonNull View itemView) {
            super(itemView);
            this.category = itemView.findViewById(R.id.category);
            this.date = itemView.findViewById(R.id.date);
        }
    }
}
