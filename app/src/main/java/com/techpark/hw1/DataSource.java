package com.techpark.hw1;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private final List<NumData> mData;
    private static DataSource sInstance;

    DataSource() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            mData.add(new NumData(i+1));
        }
    }

    NumData get(int i) {
        return mData.get(i);
    }

    void add(){
        mData.add(new NumData(mData.get(mData.size() - 1).mNumber + 1));
    }
    public List<NumData> getData() {
        return mData;
    }
    int size() {
        return mData.size();
    }

    public synchronized static DataSource getInstance() {
        if (sInstance == null) {
            sInstance = new DataSource();
        }
        return sInstance;
    }

    static class NumData {
        private int mNumber;
        private int mColor;
        private final static int ODD = Color.RED;
        private final static int EVEN = Color.BLUE;
        NumData(int number){
            this.mNumber = number;
            this.mColor = number % 2 == 0 ? ODD : EVEN;
        }

        int getNumber() {
            Log.d("GET", String.valueOf(mNumber));
            return mNumber;
        }
        int getColor() {
            return mColor;
        }
//        public void setNumber(int number) {
//            this.number = number;
//        }
//        public void setColor(int color) {
//            this.color = color;
//        }
    }
}
