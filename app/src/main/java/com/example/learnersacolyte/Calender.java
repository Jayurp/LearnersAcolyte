package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Calender extends AppCompatActivity {

    FloatingActionButton AddEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        getSupportActionBar().setTitle("Calender");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        AddEvent = findViewById(R.id.addevent);

        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoEvent();
            }
        });
    }

    public void GotoEvent()
    {
        Intent intent = new Intent(Calender.this, AddEventActivity.class);
        startActivity(intent);
    }
}