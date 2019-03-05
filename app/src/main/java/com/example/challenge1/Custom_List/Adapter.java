package com.example.challenge1.Custom_List;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challenge1.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

	private boolean isChecked;
	private List<String> titles, contents;
	Context context;

	public Adapter(List<String> titles, List<String> contents, Context context) {
		this.titles = titles;
		this.contents = contents;
		this.context = context;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_layout, viewGroup, false);
		return new ViewHolder(view);
	}



	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
		viewHolder.title.setText(titles.get(i));
		viewHolder.content.setText(contents.get(i));
		/*final int position = Integer.parseInt(titles.get(i));

		viewHolder.select.setOnCheckedChangeListener(null);
		viewHolder.select.setChecked();

		viewHolder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toast.makeText(context, titles.get(position), Toast.LENGTH_SHORT).show();
			}
		});*/
	}

	@Override
	public int getItemCount() {
		return titles.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		ImageView photo;
		TextView title, content;
		CheckBox select;


		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			this.photo = itemView.findViewById(R.id.photo);
			this.title = itemView.findViewById(R.id.title);
			this.content = itemView.findViewById(R.id.content);
			this.select = itemView.findViewById(R.id.select);
		}
	}

	public boolean getChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}
}
