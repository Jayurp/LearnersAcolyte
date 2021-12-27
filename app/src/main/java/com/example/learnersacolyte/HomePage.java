package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {

    ImageButton enotes;
    ImageButton calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        enotes = findViewById(R.id.enotes);
        calender = findViewById(R.id.scheduler);

        enotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_enotes();
            }
        });

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_calender();
            }
        });
    }

    public void start_enotes()
    {
        Intent enotes_intent = new Intent(HomePage.this, Enotes.class);
        startActivity(enotes_intent);
    }

    public void start_calender()
    {
        Intent intent = new Intent(HomePage.this, Calender.class);
        startActivity(intent);
    }
}