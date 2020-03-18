package com.techpark.hw1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements AllNumbersFragment.OnItemSelectedListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.main_frame);
        if (f != null) return;
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new AllNumbersFragment()).commit();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        Log.d("ATTACH", "onAttachFragment");
        if (fragment instanceof AllNumbersFragment) {
            Log.d("ATTACH", "instance");
            AllNumbersFragment allNumbersFragment = (AllNumbersFragment) fragment;
            allNumbersFragment.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(int pos, int color) {
        NumberFullscreenFragment newFrag = new NumberFullscreenFragment();
        Bundle args = new Bundle();
        args.putInt("number", pos);
        args.putInt("color", color);
        newFrag.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, newFrag)
                .addToBackStack(null)
                .commit();
    }
}
