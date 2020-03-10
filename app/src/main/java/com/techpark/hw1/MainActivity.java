package com.techpark.hw1;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.main_frame);
        if (f != null) return;
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new AllNumbersFragment()).commit();
    }

    public void addNewNumber(View view) {
    }
}
