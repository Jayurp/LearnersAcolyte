package com.example.learnersacolyte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class Calender extends AppCompatActivity {

    FloatingActionButton AddEvent;
    CalendarView mcalender;
    String dayCa, monthCa, yearCa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        getSupportActionBar().setTitle("Calender");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        mcalender = findViewById(R.id.CalenderV);

        AddEvent = findViewById(R.id.addevent);

        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoEvent();
            }
        });

        mcalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dayCa = Integer.toString(dayOfMonth);
                monthCa = Integer.toString(month);
                yearCa = Integer.toString(year);
            }
        });
    }

    public void GotoEvent()
    {
        Intent intent = new Intent(Calender.this, AddEventActivity.class);
        intent.putExtra("day_value", dayCa);
        intent.putExtra("month_value", monthCa);
        intent.putExtra("year_value", yearCa);
        startActivity(intent);
    }
}