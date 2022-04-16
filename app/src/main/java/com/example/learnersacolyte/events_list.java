package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class events_list extends AppCompatActivity {

    List<EventDataStructure> list = new ArrayList<>();
    RecyclerView rc;
    ShowEventRecyclerAdapter adapter;
    DbHelper db = new DbHelper(this);
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        tx = findViewById(R.id.thisText);

        Intent intent = new Intent();
        String year = getIntent().getStringExtra("year_value");
        String month = getIntent().getStringExtra("month_value");
        String day = getIntent().getStringExtra("day_value");

        tx.setText(year+" "+month+" "+day);

        String[] titles = db.gettitle(year, month, day);
        String[] time = db.getTime(year, month, day);
        String[] id = db.getID(year, month, day);

        adapter =  new ShowEventRecyclerAdapter(events_list.this, titles, time, id);

        rc = findViewById(R.id.recycler);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);
    }
}