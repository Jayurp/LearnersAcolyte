package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {

    ImageButton enotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        enotes = findViewById(R.id.enotes);

        enotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_enotes();
            }
        });
    }

    public void start_enotes()
    {
        Intent enotes_intent = new Intent(this, Enotes.class);
        startActivity(enotes_intent);
    }
}