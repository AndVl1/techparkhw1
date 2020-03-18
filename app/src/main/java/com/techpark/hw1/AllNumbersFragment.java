package com.techpark.hw1;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllNumbersFragment extends Fragment {
    private int current;
    private final int MIN = 1;
    private final int MAX = 100;
    private final int P_COLM = 3;
    private final int L_COLM = 4;
    private Adapter numAdapter;
    private OnItemSelectedListener callback;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            current = savedInstanceState.getInt("max");
        }else {
            current = MAX;
        }
        numAdapter = new Adapter(current);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_numbers, container, false);

        RecyclerView rv = v.findViewById(R.id.all_numbers);

        int columns = (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) ? P_COLM : L_COLM;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), columns);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(numAdapter);

        v.findViewById(R.id.add_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        numAdapter.add();
                    }
                }
        );
        return v;
    }

    public void addNewNumber(View view){
        numAdapter.add();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("max", current);
    }



    void setOnItemSelectedListener(OnItemSelectedListener callback){
        Log.d("ATTACH", "setOn...Listener");
        this.callback = callback;
        Log.d("ATTACH", (this.callback == null) ? "null" : "not null");
    }

    public interface OnItemSelectedListener{
        void onItemSelected(int pos, int color);
    }

    private class Adapter extends RecyclerView.Adapter<Holder>{
        private final DataSource dataList = new DataSource();

        Adapter(int current){
            if (current > MAX)
            for (int i = MAX; i < current; i++) {
                dataList.add();
            }
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater
                    .inflate(R.layout.fragment_number, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.getNumber().setText(String.valueOf(dataList.get(position).getNumber()));
            holder.getNumber().setTextColor(dataList.get(position).getColor());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        void add(){
            current++;
            dataList.add();
            numAdapter.notifyItemInserted(dataList.size() - 1);
        }
    }

    private class Holder extends RecyclerView.ViewHolder{
        private TextView mNumber;

        Holder(@NonNull View itemView) {
            super(itemView);
            mNumber = itemView.findViewById(R.id.number_fragment);
            mNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() == null) {
                        return;
                    }
                    Log.d("SET CALLBACK", callback == null ? "null" : "not null");
                    callback.onItemSelected(Integer.parseInt(((TextView) v).getText().toString()), ((TextView) v).getCurrentTextColor());
                }
            });
        }

        TextView getNumber() {
            return mNumber;
        }
    }
}
