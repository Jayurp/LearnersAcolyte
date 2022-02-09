package com.example.learnersacolyte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddEventActivity extends AppCompatActivity {


    //DbHelper db = new DbHelper(AddEventActivity.this);
    NumberPicker HourPicker, MinPicker, AmPm;
    String[] AP,minute;
    String showhour, showmin, amorpm, day, monthC, yearC;
    Button submit_btn, cancel_btn;
    EditText description;
    String GoogleIDforDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        getSupportActionBar().setTitle("Add Event");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(AddEventActivity.this);
        GoogleIDforDB = acc.getId();
        day = intent.getStringExtra("day_value");
        monthC = intent.getStringExtra("month_value");
        yearC = intent.getStringExtra("year_value");

        HourPicker = findViewById(R.id.HourPIcker);
        MinPicker = findViewById(R.id.MinPicker);
        AmPm = findViewById(R.id.AmPm);
        AP = getResources().getStringArray(R.array.AP);
        minute = getResources().getStringArray(R.array.minute);
        submit_btn = findViewById(R.id.submit_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        description = findViewById(R.id.EventDes);

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
                showhour = Integer.toString(newVal);
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
                if(newVal == 1)
                    amorpm = "am";
                else
                    amorpm = "pm";

            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreData();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back();
            }
        });

    }

    private void StoreData()
    {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(AddEventActivity.this);
        String GoogleID = acct.getId();
        HashMap<String, Object> obj = new HashMap<String, Object>();
        obj.put("Hour", showhour);
        obj.put("Minute", showmin);
        obj.put("AMorPM", amorpm);
        obj.put("Day",day);
        obj.put("Month", monthC);
        obj.put("Year", yearC);
        obj.put("Event", description.getText().toString());
        FirebaseDatabase.getInstance().getReference().child(GoogleID).push().setValue(obj);
        Toast.makeText(AddEventActivity.this, "Event Added", Toast.LENGTH_SHORT).show();
        Back();
    }

    private void Back()
    {
        Intent intent = new Intent(AddEventActivity.this, Calender.class);
        startActivity(intent);
    }

}