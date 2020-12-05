package com.example.health.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health.R;
import com.example.health.adapter.RepMaxAdapter;
import com.example.health.modal.RepMax;

import java.util.ArrayList;

public class RepMaxFragment extends Fragment {

    Button btnMinusWeightLifted, btnPlusWeightLifted, btnMinusRepsPerformed, btnPlusRepsPerformed;
    EditText edtWeightLifted, edtRepsPerformed;
    TextView tvOneRepMax, tvOneRepMaxPercentOfOneRM, tvTwoRepMaxPercentOfOneRM;

    RecyclerView recyclerViewRepMax, recyclerViewRepMaxPercentOfOneRM;
    ArrayList<RepMax> listRepMax;
    ArrayList<RepMax> listRepMaxPercentOfOneRM;
    RepMaxAdapter adapter;
    RepMaxAdapter percentAdapter;
    int oneRepMax = 279;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repmax, container, false);

        anhXa(view);

        btnMinusWeightLifted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtWeightLifted.getText().length() > 0) {
                    int weightLiftedVal = Integer.parseInt(edtWeightLifted.getText().toString());
                    if (weightLiftedVal > 5) {
                        edtWeightLifted.setText(String.valueOf(weightLiftedVal - 5));
                        setDataRepMax();
                    }
                    if(weightLiftedVal <= 5){
                        edtWeightLifted.setText("0");
                    }
                }
            }
        });

        btnPlusWeightLifted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtWeightLifted.getText().length() > 0) {
                    int weightLiftedVal = Integer.parseInt(edtWeightLifted.getText().toString());
                    if (weightLiftedVal < 999) {
                        edtWeightLifted.setText(String.valueOf(weightLiftedVal + 5));
                        setDataRepMax();
                    }
                }
            }
        });

        btnMinusRepsPerformed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtRepsPerformed.getText().length() > 0) {
                    int repsPerformedVal = Integer.parseInt(edtRepsPerformed.getText().toString());
                    if (repsPerformedVal > 1) {
                        edtRepsPerformed.setText(String.valueOf(repsPerformedVal - 1));
                        setDataRepMax();
                    }
                }
            }
        });

        btnPlusRepsPerformed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtRepsPerformed.getText().length() > 0) {
                    int repsPerformedVal = Integer.parseInt(edtRepsPerformed.getText().toString());
                    if (repsPerformedVal < 15) {
                        edtRepsPerformed.setText(String.valueOf(repsPerformedVal + 1));
                        setDataRepMax();
                    }
                    edtRepsPerformed.clearFocus();
                }
            }
        });

        edtWeightLifted.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (edtWeightLifted.getText().toString().trim().length() == 0) {
                        edtWeightLifted.setText("0");
                    }
                    setDataRepMax();
                    edtWeightLifted.clearFocus();
                }
                return false;
            }
        });


        edtRepsPerformed.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (edtRepsPerformed.getText().toString().trim().length() == 0) {
                        edtRepsPerformed.setText("1");
                    }
                    setDataRepMax();
                    edtRepsPerformed.clearFocus();
                }
                return false;
            }
        });


        return view;
    }

    private void anhXa(View view) {
        btnMinusWeightLifted = view.findViewById(R.id.btn_minus_weight_lifted);
        btnPlusWeightLifted = view.findViewById(R.id.btn_plus_weight_lifted);
        btnMinusRepsPerformed = view.findViewById(R.id.btn_minus_reps_performed);
        btnPlusRepsPerformed = view.findViewById(R.id.btn_plus_reps_performed);
        edtWeightLifted = view.findViewById(R.id.edt_weight_lifted);
        edtRepsPerformed = view.findViewById(R.id.edt_reps_performed);
        recyclerViewRepMaxPercentOfOneRM = view.findViewById(R.id.recycler_view_rep_max_percent);
        tvOneRepMax = view.findViewById(R.id.tv_one_rep_max);
        tvOneRepMaxPercentOfOneRM = view.findViewById(R.id.tv_one_rep_max_105_percent);
        tvTwoRepMaxPercentOfOneRM = view.findViewById(R.id.tv_two_rep_max_102_5_percent);

        if (listRepMax == null) {
            listRepMax = new ArrayList<>();
        }
        if (listRepMaxPercentOfOneRM == null) {
            listRepMaxPercentOfOneRM = new ArrayList<>();
        }

        recyclerViewRepMax = view.findViewById(R.id.recycler_view_rep_max);
        adapter = new RepMaxAdapter(view.getContext(), listRepMax);

        recyclerViewRepMax.setHasFixedSize(true);
        recyclerViewRepMax.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewRepMax.setAdapter(adapter);


        percentAdapter = new RepMaxAdapter(view.getContext(), listRepMaxPercentOfOneRM);
        recyclerViewRepMaxPercentOfOneRM.setHasFixedSize(true);
        recyclerViewRepMaxPercentOfOneRM.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewRepMaxPercentOfOneRM.setAdapter(percentAdapter);
        setDataRepMax();

    }

    private void setDataRepMax() {
        listRepMax.clear();

        int weight = Integer.parseInt(edtWeightLifted.getText().toString());
        int rep = Integer.parseInt(edtRepsPerformed.getText().toString());

        oneRepMax = (int) Math.round(weight / (1.0278 - 0.0278 * rep));
        tvOneRepMax.setText(String.valueOf(oneRepMax));


        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.95) + " kg", "2RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.93) + " kg", "3RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.90) + " kg", "4RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.87) + " kg", "5RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.85) + " kg", "6RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.83) + " kg", "7RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.80) + " kg", "8RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.77) + " kg", "9RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.75) + " kg", "10RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.73) + " kg", "11RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.70) + " kg", "12RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.67) + " kg", "13RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.65) + " kg", "14RM"));
        listRepMax.add(new RepMax((int) Math.round(oneRepMax * 0.63) + " kg", "15RM"));

        adapter.notifyDataSetChanged();

        setDataPercentOneRM(oneRepMax);
    }

    private void setDataPercentOneRM(int oneRepMax) {
        listRepMaxPercentOfOneRM.clear();

        String percent_105 = (int) Math.round(oneRepMax * 1.05) + " kg";
        String percent_102_5 = (int) Math.round(oneRepMax * 1.025) + " kg";
        tvOneRepMaxPercentOfOneRM.setText(percent_105);
        tvTwoRepMaxPercentOfOneRM.setText(percent_102_5);

        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.95) + " kg", "95%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.90) + " kg", "90%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.85) + " kg", "85%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.80) + " kg", "80%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.75) + " kg", "75%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.70) + " kg", "70%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.65) + " kg", "65%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.60) + " kg", "60%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.55) + " kg", "55%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.50) + " kg", "50%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.45) + " kg", "45%"));
        listRepMaxPercentOfOneRM.add(new RepMax((int) Math.round(oneRepMax * 0.40) + " kg", "40%"));


        percentAdapter.notifyDataSetChanged();
    }
}
