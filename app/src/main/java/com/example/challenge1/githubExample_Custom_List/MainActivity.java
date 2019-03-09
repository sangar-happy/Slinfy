package com.example.challenge1.githubExample_Custom_List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.challenge1.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	public RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private CheckBox checkBox;
	private Adapter.ViewHolder viewHolder;

	// dummy list of items to be populated manually
	List<Model> items = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_github);

		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		final Adapter adapter = new Adapter();
		layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);
		checkBox = findViewById(R.id.checkBox);



		fillItems();

		adapter.loadItems(items);

		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

			}
		});
	}

	private void fillItems() {
		for (int x = 0; x <=100; x++) {
			Model model = new Model();
			model.setPosition(x+1);

			items.add(model);
		}
	}
}
