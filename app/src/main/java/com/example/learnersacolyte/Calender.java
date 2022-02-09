package com.example.learnersacolyte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Calender extends AppCompatActivity {

    FloatingActionButton AddEvent,listbtn;
    CalendarView mcalender;
    String dayCa, monthCa, yearCa;
    DbHelper db = new DbHelper(Calender.this);
    AlertDialog.Builder DialogBuilder;
    AlertDialog dialog;

    @Override
    public void onBackPressed() {
        GoBack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        listbtn =  findViewById(R.id.list);

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
                monthCa = Integer.toString(month+1);
                yearCa = Integer.toString(year);
            }
        });

        fetchdata();

        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopup();
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

    private void fetchdata()
    {
        try
        {
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Calender.this);
            String GoogleID = acct.getId();
            FirebaseDatabase.getInstance().getReference().child(GoogleID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        for (DataSnapshot snapshot1:snapshot.getChildren())
                        {
                            FetchFromFIrebase f1 = snapshot1.getValue(FetchFromFIrebase.class);
                            db.insertFireBaseDataInSQ(f1.getDay(), f1.getMonth(), f1.getYear(), f1.getMinute(), f1.getHour(), f1.getAMorPM(), f1.getEvent());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        catch (OutOfMemoryError e)
        {
            Toast.makeText(Calender.this, "Out of Memory", Toast.LENGTH_SHORT).show();
        }
    }

    public void GoBack()
    {
        Intent intent =  new Intent(Calender.this, HomePage.class);
        startActivity(intent);
    }

    public void showpopup()
    {
        DialogBuilder =  new AlertDialog.Builder(Calender.this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.show_events, null);

        DialogBuilder.setView(contactPopupView);
        dialog = DialogBuilder.create();
        dialog.show();
    }
}