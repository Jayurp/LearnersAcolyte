package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Calender extends AppCompatActivity {

    Button AddEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        AddEvent = findViewById(R.id.AddEvent);

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