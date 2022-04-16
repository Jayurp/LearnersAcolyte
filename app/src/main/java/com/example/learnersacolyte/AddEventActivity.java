package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddEventActivity extends AppCompatActivity {


    //DbHelper db = new DbHelper(AddEventActivity.this);
    NumberPicker HourPicker, MinPicker, AmPm;
    String[] AP,minute;
    String showhour = "01", showmin = "00", amorpm = "am", day, monthC, yearC, zero = Integer.toString(0);
    Button submit_btn, cancel_btn;
    EditText description, title;
    String GoogleIDforDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        Intent intent = getIntent();
        GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(AddEventActivity.this);
        GoogleIDforDB = acc.getId();
        day = intent.getStringExtra("day_value");
        monthC = intent.getStringExtra("month_value");
        yearC = intent.getStringExtra("year_value");

        HourPicker = findViewById(R.id.hour_for_add);
        MinPicker = findViewById(R.id.min_for_add);
        AmPm = findViewById(R.id.format_for_add);
        AP = getResources().getStringArray(R.array.AP);
        minute = getResources().getStringArray(R.array.minute);
        submit_btn = findViewById(R.id.submit_for_add);
        cancel_btn = findViewById(R.id.cancel_for_add);
        description = findViewById(R.id.des_for_add);
        title = findViewById(R.id.title_for_add);

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
                if(newVal < 10)
                {
                    showhour = zero + Integer.toString(newVal);
                }
                else
                    showhour = Integer.toString(newVal);
            }
        });

        MinPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(newVal < 10)
                {
                    showmin = zero + Integer.toString(newVal);
                }
                else
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
        String id = yearC + monthC + day + showhour + showmin + amorpm;
        obj.put("Hour", showhour);
        obj.put("Minute", showmin);
        obj.put("AMorPM", amorpm);
        obj.put("Day",day);
        obj.put("Month", monthC);
        obj.put("Year", yearC);
        obj.put("Event", description.getText().toString());
        obj.put("Title", title.getText().toString());
        obj.put("ID",id);
        FirebaseDatabase.getInstance().getReference().child(GoogleID).child(id).setValue(obj);
        Toast.makeText(AddEventActivity.this, "Event Added", Toast.LENGTH_SHORT).show();
        Back();
    }

    private void Back()
    {
        Intent intent = new Intent(AddEventActivity.this, Calender.class);
        startActivity(intent);
    }

}