package com.example.challenge1.fragments;


import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.challenge1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    private static final String TAG = EventsFragment.class.getSimpleName();

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private int nonSelectedColor;
    private int selectedColor;

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

//        textView1 = view.findViewById(R.id.detail_item_1);
//        textView2 = view.findViewById(R.id.detail_item_2);
//        textView3 = view.findViewById(R.id.detail_item_3);

        selectedColor = ContextCompat.getColor(getContext(), R.color.warning);
        nonSelectedColor = ContextCompat.getColor(getContext(), R.color.black);

        return view;
    }

    public void onMasterItemClicked(int masterId) {
        // reset colors
//        textView1.setTextColor(nonSelectedColor);
//        textView2.setTextColor(nonSelectedColor);
//        textView3.setTextColor(nonSelectedColor);

        switch (masterId) {
            case 1:
                textView1.setTextColor(selectedColor);
                break;

            case 2:
                textView2.setTextColor(selectedColor);
                break;

            case 3:
                textView3.setTextColor(selectedColor);
                break;

            default:
                Log.d(TAG, "unknown master ID");

        }
    }

}
