package com.example.challenge1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.challenge1.R;
import com.example.challenge1.models.User;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    ArrayList<User> userList;

    public UserListAdapter(ArrayList<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_layout, null, false);

        view.setLayoutParams(new RecyclerView.LayoutParams
                        (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.profileImage.setImageResource(userList.get(position).getProfilePic());
        holder.name.setText(userList.get(position).getName());
        holder.message.setText(userList.get(position).getMessage());
        holder.time.setText(userList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView profileImage;
        public TextView name;
        public TextView message;
        public TextView time;

        public ViewHolder(View view) {
            super(view);
            profileImage = view.findViewById(R.id.profile_pic);
            name = view.findViewById(R.id.name);
            message = view.findViewById(R.id.message);
            time = view.findViewById(R.id.time);
        }
    }
}
