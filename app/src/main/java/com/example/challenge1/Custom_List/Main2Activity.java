package com.example.challenge1.Custom_List;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.challenge1.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

	RecyclerView recyclerView;
	RecyclerView.Adapter adapter;
	RecyclerView.LayoutManager layoutManager;
	List<String> titles, contents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		recyclerView = findViewById(R.id.rlist);

		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		titles = new ArrayList();
		contents = new ArrayList();

		titles.add("Title 1");
		contents.add("Content 1");
		titles.add("Title 2");
		contents.add("Content 2");
		titles.add("Title 3");
		contents.add("Content 3");
		titles.add("Title 4");
		contents.add("Content 4");
		titles.add("Title 5");
		contents.add("Content 5");
		titles.add("Title 6");
		contents.add("Content 6");
		titles.add("Title 7");
		contents.add("Content 7");
		titles.add("Title 8");
		contents.add("Content 8");
		titles.add("Title 9");
		contents.add("Content 9");
		titles.add("Title 10");
		contents.add("Content 10");
		titles.add("Title 11");
		contents.add("Content 11");
		titles.add("Title 12");
		contents.add("Content 12");
		titles.add("Title 13");
		contents.add("Content 13");
		titles.add("Title 14");
		contents.add("Content 14");
		titles.add("Title 15");
		contents.add("Content 15");
		titles.add("Title 16");
		contents.add("Content 16");
		titles.add("Title 17");
		contents.add("Content 17");
		titles.add("Title 18");
		contents.add("Content 18");
		titles.add("Title 19");
		contents.add("Content 19");

		adapter = new Adapter(titles, contents, this);
		recyclerView.setAdapter(adapter);
//		recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//			@Override
//			public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
//				Toast.makeText(Main2Activity.this, "hiii", Toast.LENGTH_SHORT).show();
//				return false;
//			}
//
//			@Override
//			public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
//
//			}
//
//			@Override
//			public void onRequestDisallowInterceptTouchEvent(boolean b) {
//
//			}
//		});

	}
}
