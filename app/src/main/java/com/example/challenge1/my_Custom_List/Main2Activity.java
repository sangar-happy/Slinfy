package com.example.challenge1.my_Custom_List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.challenge1.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

	RecyclerView recyclerView;
	RecyclerView.Adapter adapter = new Adapter();
	List<String> titles, contents;
	CheckBox checkBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		recyclerView = findViewById(R.id.rlist);
		checkBox = findViewById(R.id.select);

		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		final List<DataItem> dataItemList = getInitDataList();
		((Adapter) adapter).loadItems(dataItemList);
		recyclerView.setAdapter(adapter);


		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				int size = dataItemList.size();
				for(int i=0;i<size;i++)
				{
					DataItem dataItem = dataItemList.get(i);
					dataItem.setChecked(isChecked);
				}

				adapter.notifyDataSetChanged();
			}
		});

	}

	private List<DataItem> getInitDataList() {
		List<DataItem> dataItemList = new ArrayList<>();

		DataItem dataItem = new DataItem();
		dataItem.setTitle("Hello");
		dataItem.setContent("YOLO");
		dataItem.setChecked(false);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);
		dataItemList.add(dataItem);

		return dataItemList;
	}
}


//		titles = new ArrayList();
//		contents = new ArrayList();
//
//		titles.add("Title 1");
//		contents.add("Content 1");
//		titles.add("Title 2");
//		contents.add("Content 2");
//		titles.add("Title 3");
//		contents.add("Content 3");
//		titles.add("Title 4");
//		contents.add("Content 4");
//		titles.add("Title 5");
//		contents.add("Content 5");
//		titles.add("Title 6");
//		contents.add("Content 6");
//		titles.add("Title 7");
//		contents.add("Content 7");
//		titles.add("Title 8");
//		contents.add("Content 8");
//		titles.add("Title 9");
//		contents.add("Content 9");
//		titles.add("Title 10");
//		contents.add("Content 10");
//		titles.add("Title 11");
//		contents.add("Content 11");
//		titles.add("Title 12");
//		contents.add("Content 12");
//		titles.add("Title 13");
//		contents.add("Content 13");
//		titles.add("Title 14");
//		contents.add("Content 14");
//		titles.add("Title 15");
//		contents.add("Content 15");
//		titles.add("Title 16");
//		contents.add("Content 16");
//		titles.add("Title 17");
//		contents.add("Content 17");
//		titles.add("Title 18");
//		contents.add("Content 18");
//		titles.add("Title 19");
//		contents.add("Content 19");
//
//
//		int i = 0;
//		for(DataItem dataItem : dataItemList) {
//			dataItem.setTitle(titles.get(i));
//			i++;
//		}
//
//		i = 0;
//		for(DataItem dataItem : dataItemList) {
//			dataItem.setTitle(titles.get(i));
//			i++;
//		}dataItemList.add(dataItem);
//
//		((Adapter) adapter).loadItems(dataItemList);
//		recyclerView.setAdapter(adapter);

