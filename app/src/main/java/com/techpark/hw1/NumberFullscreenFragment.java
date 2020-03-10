package com.techpark.hw1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumberFullscreenFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_number_fullscreen, container, false);

        Log.d("FULLSCREEN", "null");

        if (getArguments() == null){
            return v;
        }
        int num = getArguments().getInt("number", -1);
        int color = getArguments().getInt("color", Color.BLACK);
        TextView view = v.findViewById(R.id.numberView);
        view.setText(String.valueOf(num));
        view.setTextColor(color);

        Log.d("Color", String.valueOf(color));

        return v;
    }
}
