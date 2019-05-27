package com.example.challenge1.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challenge1.R;
import com.example.challenge1.database.entity.Event;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<Event> allEvents = new ArrayList<>();

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_layout, parent, false);
        return new EventHolder(eventView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event currentNote = allEvents.get(position);
        holder.profile_pic.setImageResource(R.mipmap.ic_launcher);
        holder.title.setText(currentNote.getEventCategory());
        holder.timing.setText(currentNote.getEventTitle());
        holder.right_arrow.setImageResource(R.drawable.ic_arrow_right);
    }

    @Override
    public int getItemCount() {
        return allEvents.size();
    }

    public void setEventz(List<Event> events) {
        this.allEvents = events;
        notifyDataSetChanged();
    }

    class EventHolder extends RecyclerView.ViewHolder {

        CircleImageView profile_pic;
        TextView title
                , timing;
        ImageView right_arrow;

        public EventHolder(@NonNull View itemView) {
            super(itemView);
            this.profile_pic = itemView.findViewById(R.id.profile_pic);
            this.title = itemView.findViewById(R.id.title);
            this.timing = itemView.findViewById(R.id.timing);
            this.right_arrow = itemView.findViewById(R.id.right_arrow);
        }
    }
}
