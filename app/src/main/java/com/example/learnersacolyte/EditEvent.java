package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

public class EditEvent extends AppCompatActivity {

    EditText title, event;
    Button cancel, submit;
    NumberPicker hour, minute, format;
    String id, str_title, str_event, str_hour, str_min, str_format, str_year, str_month, str_date;
    DbHelper db = new DbHelper(this);
    String hold_title, hold_event, hold_hour, hold_min, hold_format, hold_year, hold_month, hold_date;
    String zero = Integer.toString(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        title = findViewById(R.id.title_for_edit);
        event = findViewById(R.id.des_for_edit);
        cancel = findViewById(R.id.cancel_for_edit);
        submit = findViewById(R.id.submit_for_edit);
        hour = findViewById(R.id.hour_for_edit);
        minute = findViewById(R.id.min_for_edit);
        format = findViewById(R.id.format_for_edit);

        String[] ampm = getResources().getStringArray(R.array.AP);
        Intent intent = new Intent();
        id = getIntent().getStringExtra("id");

        Cursor cursor = db.FetchFullEvent(id);

        while(cursor.moveToNext())
        {
            str_title = cursor.getString(0);
            str_event = cursor.getString(7);
            str_hour = cursor.getString(4);
            str_min = cursor.getString(5);
            str_format = cursor.getString(6);
            str_date = cursor.getString(1);
            str_month = cursor.getString(2);
            str_year = cursor.getString(3);
        }

        int hour_integer = Integer.parseInt(str_hour);
        int min_integer = Integer.parseInt(str_min);

        if(hour_integer < 10)
            hold_hour = zero + str_hour;
        else
            hold_hour = str_hour;

        if(min_integer < 10)
            hold_min = zero + str_min;
        else
            hold_min = str_min;

        hold_format = "am";

        hour.setMaxValue(12);
        hour.setMinValue(1);
        minute.setMaxValue(59);
        minute.setMinValue(00);
        format.setMaxValue(2);
        format.setMinValue(1);
        format.setDisplayedValues(ampm);


        title.setText(str_title);
        event.setText(str_event);
        hour.setValue(hour_integer);
        minute.setValue(min_integer);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();
            }
        });

        hold_title = title.getText().toString();
        hold_event = event.getText().toString();

        hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(newVal < 10)
                {
                    hold_hour = zero + Integer.toString(newVal);
                }
                else
                    hold_hour = Integer.toString(newVal);
            }
        });

        minute.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(newVal < 10)
                {
                    hold_min = zero + Integer.toString(newVal);
                }
                else
                    hold_min = Integer.toString(newVal);
            }
        });

        format.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(newVal == 1)
                    hold_format = "am";
                else
                    hold_format = "pm";
            }
        });

        hold_year = str_year;

        if(str_month.length() == 1)
            hold_month = zero + str_month;
        else
            hold_month = str_month;

        if(str_date.length() == 1)
            hold_date = zero + str_date;
        else
            hold_date = str_date;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData();
            }
        });
    }

    public void GoBack()
    {
        Intent intent = new Intent(EditEvent.this, Calender.class);
        startActivity(intent);
    }

    public void UpdateData()
    {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(EditEvent.this);
        String GoogleID = acct.getId();
        HashMap<String, Object> obj = new HashMap<String, Object>();
        String newID = hold_year + hold_month + hold_date + hold_hour + hold_min + hold_format;
        obj.put("Hour", hold_hour);
        obj.put("Minute", hold_min);
        obj.put("AMorPM", hold_format);
        obj.put("Day",hold_date);
        obj.put("Month", hold_month);
        obj.put("Year", hold_year);
        obj.put("Event", event.getText().toString());
        obj.put("Title", title.getText().toString());
        obj.put("ID",newID);
        FirebaseDatabase.getInstance().getReference().child(GoogleID).child(id).removeValue();
        FirebaseDatabase.getInstance().getReference().child(GoogleID).child(newID).setValue(obj);
        Toast.makeText(EditEvent.this, "Event Updated", Toast.LENGTH_SHORT).show();
        GoBack();
    }
}