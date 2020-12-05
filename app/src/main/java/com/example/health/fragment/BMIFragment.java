package com.example.health.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.health.R;

import java.util.Objects;

public class BMIFragment extends Fragment {

    Button btnMinusHeight,btnPlusHeight,btnMinusWeight,btnPlusWeight,btnCalculatorBMI, btnShowBMITable;
    EditText edtHeight,edtWeight;
    TextView tvBMIResultProgressbar,tvBMIResult,tvBMIStatus,tvComment;
    LinearLayout layoutResult;
    ProgressBar progressBarBMIResult;
    int i = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        anhXa(view);

        btnMinusHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtHeight.getText().length() > 0 ){
                    int height = Integer.parseInt(edtHeight.getText().toString());
                    if(height > 0){
                        edtHeight.setText(String.valueOf(height-5));
                    }
                    if(height <= 5){
                        edtHeight.setText("0");
                    }
                }
            }
        });
        btnPlusHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtHeight.getText().length() > 0 ){
                    int height = Integer.parseInt(edtHeight.getText().toString());
                    if(height < 300){
                        edtHeight.setText(String.valueOf(height+5));
                    }
                }
            }
        });

        btnMinusWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtWeight.getText().toString().trim().length() > 0){
                    int weight = Integer.parseInt(edtWeight.getText().toString().trim());
                    if(weight > 1){
                        edtWeight.setText(String.valueOf(weight-1));
                    }
                }
            }
        });

        btnPlusWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtWeight.getText().toString().trim().length() > 0){
                    int weight = Integer.parseInt(edtWeight.getText().toString().trim());
                    if(weight < 300){
                        edtWeight.setText(String.valueOf(weight + 1));
                    }
                }
            }
        });


        // clear focus
        edtHeight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (edtHeight.getText().toString().trim().length() == 0) {
                        edtHeight.setText("1");
                    }
                    edtHeight.clearFocus();
                }
                return false;
            }
        });
        edtWeight.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    if (edtWeight.getText().toString().trim().length() == 0) {
                        edtWeight.setText("1");
                    }
                    edtWeight.clearFocus();
                }
                return false;
            }
        });

        btnCalculatorBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtHeight.clearFocus();
                edtWeight.clearFocus();
                i = 0;
                float height = Integer.parseInt(edtHeight.getText().toString())/100f;
                int weight = Integer.parseInt(edtWeight.getText().toString());

                final float BMIResult = (float) (weight/Math.pow(height,2));
                final int BMIResult2 = (int) BMIResult;
                tvBMIResultProgressbar.setText(String.valueOf(BMIResult));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(i < BMIResult2){
                            progressBarBMIResult.setProgress(i);
                            i++;
                            handler.postDelayed(this,0);
                        }
                        else{
                            handler.removeCallbacks(this);
                        }
                    }
                },0);
                hideKeyboard(view);
                layoutResult.setVisibility(View.VISIBLE);
                tvBMIResult.setText(String.valueOf(BMIResult));
                resultForYourBMI(BMIResult);
            }
        });

        btnShowBMITable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogBMITable();
            }
        });

        return  view;
    }

    private void showDialogBMITable() {
        final Dialog dialog = new Dialog(Objects.requireNonNull(getContext()));
        dialog.setContentView(R.layout.dialog_table_bmi);

        dialog.show();
    }

    private void resultForYourBMI(float bmiResult) {
        tvComment.setVisibility(View.VISIBLE);
        if(bmiResult < 18.5){
            tvBMIStatus.setText(R.string.text_status_underweight);
            tvComment.setText(R.string.text_comment_underweight);
        }else if(18.5 <= bmiResult && bmiResult < 25){
            tvBMIStatus.setText(R.string.text_status_normal_weight);
            tvComment.setText(R.string.text_comment_normal_weight);
        }else if(25 <= bmiResult && bmiResult < 30){
            tvBMIStatus.setText(R.string.text_status_pre_obesity);
            tvComment.setText(R.string.text_comment_pre_obesity);
        }else if(30 <= bmiResult && bmiResult < 35){
            tvBMIStatus.setText(R.string.text_status_obesity_class_1);
            tvComment.setText(R.string.text_comment_obesity_class_1);
        }else if(35 <= bmiResult && bmiResult < 40){
            tvBMIStatus.setText(R.string.text_status_obesity_class_2);
            tvComment.setText(R.string.text_comment_obesity_class_2);
        }else{
            tvBMIStatus.setText(R.string.text_status_obesity_class_3);
            tvComment.setText(R.string.text_comment_obesity_class_3);
        }

    }

    private void anhXa(View view) {
        btnMinusHeight = view.findViewById(R.id.btn_minus_height);
        btnPlusHeight = view.findViewById(R.id.btn_plus_height);
        btnMinusWeight = view.findViewById(R.id.btn_minus_weight);
        btnPlusWeight = view.findViewById(R.id.btn_plus_weight);
        btnCalculatorBMI = view.findViewById(R.id.btn_calculate_bmi);
        edtHeight = view.findViewById(R.id.edt_height);
        edtWeight = view.findViewById(R.id.edt_weight);
        tvBMIResultProgressbar = view.findViewById(R.id.tv_bmi_result_progress_bar);
        tvBMIResult = view.findViewById(R.id.tv_bmi_result);
        progressBarBMIResult = view.findViewById(R.id.progress_bar_bmi_result);
        layoutResult = view.findViewById(R.id.layout_result);
        tvBMIStatus = view.findViewById(R.id.tv_bmi_status);
        tvComment = view.findViewById(R.id.tv_comment);
        btnShowBMITable = view.findViewById(R.id.btn_show_bmi_table);
    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }

}
