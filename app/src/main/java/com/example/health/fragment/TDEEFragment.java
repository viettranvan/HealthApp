package com.example.health.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.health.R;

import java.util.Objects;

public class TDEEFragment extends Fragment {

    Spinner spinnerActivityLevelsForTheDay;

    Button btnMinusHeight, btnPlusHeight, btnMinusWeight, btnPlusWeight, btnCalculatorTDEE, btnMinusOld, btnPlusOld;
    EditText edtHeight, edtWeight,edtOld;
    TextView tvBMRResult, tvTDEEResult, tvBMIStatus, tvComment;
    float indexActivityLevelsForTheDay = 1.2f;
    boolean gender = false;
    LinearLayout layoutResultTDEE;
    RadioButton male, female;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tdee, container, false);

        anhXa(view);

        btnMinusHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtHeight.getText().length() > 0) {
                    int height = Integer.parseInt(edtHeight.getText().toString());
                    if (height > 0) {
                        edtHeight.setText(String.valueOf(height - 5));
                    }
                    if (height <= 5) {
                        edtHeight.setText("0");
                    }
                }
            }
        });
        btnPlusHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtHeight.getText().length() > 0) {
                    int height = Integer.parseInt(edtHeight.getText().toString());
                    if (height < 300) {
                        edtHeight.setText(String.valueOf(height + 5));
                    }
                }
            }
        });

        btnMinusWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtWeight.getText().toString().trim().length() > 0) {
                    int weight = Integer.parseInt(edtWeight.getText().toString().trim());
                    if (weight > 1) {
                        edtWeight.setText(String.valueOf(weight - 1));
                    }
                }
            }
        });

        btnPlusWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtWeight.getText().toString().trim().length() > 0) {
                    int weight = Integer.parseInt(edtWeight.getText().toString().trim());
                    if (weight < 300) {
                        edtWeight.setText(String.valueOf(weight + 1));
                    }
                }
            }
        });


        btnMinusOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtOld.getText().toString().trim().length() > 0) {
                    int weight = Integer.parseInt(edtOld.getText().toString().trim());
                    if (weight > 19) {
                        edtOld.setText(String.valueOf(weight - 1));
                    }
                }
            }
        });

        btnPlusOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtOld.getText().toString().trim().length() > 0) {
                    int weight = Integer.parseInt(edtOld.getText().toString().trim());
                    if (weight < 70) {
                        edtOld.setText(String.valueOf(weight + 1));
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
        edtOld.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    setDataOld();
                }
                return false;
            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = false;
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = true;
            }
        });


        spinnerActivityLevelsForTheDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        indexActivityLevelsForTheDay = 1.2f;
                        break;
                    case 1:
                        indexActivityLevelsForTheDay = 1.375f;
                        break;
                    case 2:
                        indexActivityLevelsForTheDay = 1.55f;
                        break;
                    case 3:
                        indexActivityLevelsForTheDay = 1.725f;
                        break;
                    case 4:
                        indexActivityLevelsForTheDay = 1.9f;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCalculatorTDEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtOld.clearFocus();
                edtHeight.clearFocus();
                edtWeight.clearFocus();
                layoutResultTDEE.setVisibility(View.VISIBLE);

                int height = Integer.parseInt(edtHeight.getText().toString());
                int weight = Integer.parseInt(edtWeight.getText().toString());
                int old = Integer.parseInt(edtOld.getText().toString());


                float BMR,TDEE;
                if(gender){
                    BMR = (float) ((9.99*weight) + (6.25*height) - (4.92*old) + 165);
                }else {
                    BMR = (float) ((9.99*weight) + (6.25*height) - (4.92*old) - 5);
                    tvBMRResult.setText(String.valueOf(BMR));
                }
                TDEE = BMR * indexActivityLevelsForTheDay;

                tvBMRResult.setText(String.valueOf(BMR));
                tvTDEEResult.setText(String.valueOf(TDEE));

                hideKeyboard(view);
            }
        });

        return view;
    }

    private void setDataOld() {
        if (edtOld.getText().toString().trim().length() == 0 ||
                Integer.parseInt(edtOld.getText().toString().trim()) < 19) {
            edtOld.setText(R.string.text_default_val_old);
        }
        edtOld.clearFocus();
    }

    private void anhXa(View view) {
        btnMinusHeight = view.findViewById(R.id.btn_minus_height);
        btnPlusHeight = view.findViewById(R.id.btn_plus_height);
        btnMinusWeight = view.findViewById(R.id.btn_minus_weight);
        btnPlusWeight = view.findViewById(R.id.btn_plus_weight);
        btnMinusOld = view.findViewById(R.id.btn_minus_old);
        btnPlusOld = view.findViewById(R.id.btn_plus_old);
        edtHeight = view.findViewById(R.id.edt_height);
        edtWeight = view.findViewById(R.id.edt_weight);
        edtOld = view.findViewById(R.id.edt_old);
        spinnerActivityLevelsForTheDay = view.findViewById(R.id.spinner_activity_levels_for_the_day);
        btnCalculatorTDEE = view.findViewById(R.id.btn_calculate_tdee);
        tvTDEEResult = view.findViewById(R.id.tv_result_tdee);
        layoutResultTDEE = view.findViewById(R.id.layout_result_tdee);
        tvBMRResult = view.findViewById(R.id.tv_result_bmr);
        male = view.findViewById(R.id.rd_gender_male_change_info);
        female = view.findViewById(R.id.rd_gender_female_change_info);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.activity_levels_for_the_day));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivityLevelsForTheDay.setAdapter(adapter);
    }
    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
}
