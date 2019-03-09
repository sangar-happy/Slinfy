package com.example.challenge1.My_Custom_List;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.challenge1.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

	private List<DataItem> dataItemList = new ArrayList<>();
	private Context context;

	public Adapter() {}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

		context = viewGroup.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.sample_layout, viewGroup, false);

		return new ViewHolder(view);
	}



	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
		viewHolder.bind(i);
	}

	@Override
	public int getItemCount() {
		return (dataItemList == null ? 0 : dataItemList.size());
	}

	void loadItems(List<DataItem> dataItemList) {
		this.dataItemList = dataItemList;
		notifyDataSetChanged();
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

		void bind(int position) {
			title.setText(dataItemList.get(position).getTitle());
			content.setText(dataItemList.get(position).getContent());
			select.setChecked(dataItemList.get(position).isChecked());
			photo.setImageResource(R.mipmap.ic_launcher_round);
		}

		@Override
		public void onClick(View v) {
			int adapterPosition = getAdapterPosition();
			if(dataItemList.get(adapterPosition).isChecked()) {
				dataItemList.get(adapterPosition).setChecked(false);
			} else {
				dataItemList.get(adapterPosition).setChecked(true);
			}
		}
	}
}
