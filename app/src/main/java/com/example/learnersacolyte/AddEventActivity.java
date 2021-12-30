package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddEventActivity extends AppCompatActivity {

    NumberPicker HourPicker, MinPicker, AmPm;
    String[] AP,minute;
    TextView ShowTime;
    String hour, showmin, mergestring, amorpm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        getSupportActionBar().setTitle("Add Event");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        HourPicker = findViewById(R.id.HourPIcker);
        MinPicker = findViewById(R.id.MinPicker);
        AmPm = findViewById(R.id.AmPm);
        AP = getResources().getStringArray(R.array.AP);
        minute = getResources().getStringArray(R.array.minute);

        HourPicker.setMaxValue(12);
        HourPicker.setMinValue(1);
        MinPicker.setMaxValue(59);
        MinPicker.setMinValue(00);
        MinPicker.setDisplayedValues(minute);
        AmPm.setMaxValue(2);
        AmPm.setMinValue(1);
        AmPm.setDisplayedValues(AP);

        HourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                hour = Integer.toString(newVal);
            }
        });

        MinPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                showmin = Integer.toString(newVal);
            }
        });

        AmPm.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(oldVal == 1)
                    amorpm = "PM";
                else
                    amorpm = "AM";
            }
        });
    }
}