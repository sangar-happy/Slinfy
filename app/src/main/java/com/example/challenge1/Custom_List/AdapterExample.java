/*
package com.example.challenge1.Custom_List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.challenge1.R;

import java.util.List;

public class AdapterExample extends RecyclerView.Adapter<AdapterExample.ViewHolder> {

//	public interface OnItemClickListener {
//		void onItemClick(ContentItem item);
//	}
//
//	private final List<ContentItem> items;
//	private final OnItemClickListener listener;
//
//	public ContentAdapter(List<ContentItem> items, OnItemClickListener listener) {
//		this.items = items;
//		this.listener = listener;
//	}

	List<String> titles, contents;

	public AdapterExample() {
		titles =

	}

	@Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_layout, parent, false);
		return new ViewHolder(v);
	}

	@Override public void onBindViewHolder(ViewHolder holder, int position) {

	}

	@Override public int getItemCount() {
		return items.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {

		private TextView name;
		private ImageView image;

		public ViewHolder(View itemView) {
			super(itemView);
			name = (TextView) itemView.findViewById(R.id.name);
			image = (ImageView) itemView.findViewById(R.id.image);
		}

		public void bind(final ContentItem item, final OnItemClickListener listener) {
			name.setText(item.name);
			Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override public void onClick(View v) {
					listener.onItemClick(item);
				}
			});
		}
	}
}
*/
