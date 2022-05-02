package com.example.learnersacolyte;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Calender extends AppCompatActivity {

    FloatingActionButton AddEvent,listbtn;
    CalendarView mcalender;
    String dayCa, monthCa, yearCa;
    DbHelper db = new DbHelper(Calender.this);
    AlertDialog.Builder DialogBuilder;
    AlertDialog dialog;
    ShowEventRecyclerAdapter adapter;
    List<EventDataStructure> EventTITLE = new ArrayList<>();
    TextView check;
    String hourForAlarm , minForAlarm;

    @Override
    public void onBackPressed() {
        GoBack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        listbtn =  findViewById(R.id.list);

        check = findViewById(R.id.check);

        mcalender = findViewById(R.id.CalenderV);

        AddEvent = findViewById(R.id.addevent);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = dateFormat.format(new Date(mcalender.getDate()));
        yearCa = date.substring(0,4);
        monthCa = date.substring(5,7);
        dayCa = date.substring(8,10);
        check.setText(yearCa+"/"+monthCa+"/"+dayCa);

        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoEvent();
            }
        });

        listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSHowEvents();
            }
        });

        mcalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String zero = Integer.toString(0);

                if(dayOfMonth < 10)
                {
                    dayCa = zero + Integer.toString(dayOfMonth);
                }
                else dayCa = Integer.toString(dayOfMonth);

                String finale_month;
                if(month+1 < 10)
                {
                    String m = Integer.toString(month+1);
                    finale_month = zero + m;
                }
                else
                    finale_month = Integer.toString(month+1);
                monthCa = finale_month;
                yearCa = Integer.toString(year);
                EventDataStructure obj = new EventDataStructure();
                EventTITLE = db.ReadTitle(yearCa, monthCa, dayCa);
                check.setText(yearCa+"/"+monthCa+"/"+dayCa);
            }
        });

        fetchdata();

        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy/MM/dd");
        String todayDate = currentDate.format(new Date());
        String todayDay = todayDate.substring(8,10);
        if(Integer.parseInt(todayDay) < 10)
            todayDay = todayDate.substring(9,10);
        String todayMonth = todayDate.substring(5,7);
        if(Integer.parseInt(todayMonth) < 10)
            todayMonth = todayDate.substring(6,7);
        String todayYear = todayDate.substring(0,4);

        Cursor events_for_notification = db.fetchByDate(todayDay, todayMonth, todayYear);

        if(events_for_notification != null)
        {
            int count = events_for_notification.getColumnCount();
            for(int i = 0; i < count; i++)
            {
                while(events_for_notification.moveToNext())
                {
                    String format_to_convert = events_for_notification.getString(6);

                    if(format_to_convert.equals("am") && Integer.parseInt(events_for_notification.getString(4)) == 12)
                    {
                        hourForAlarm = "00";
                        if(events_for_notification.getString(5).length() == 1)
                            minForAlarm = "0" + events_for_notification.getString(5);
                        else
                            minForAlarm = events_for_notification.getString(5);
                    }

                    else if(format_to_convert.equals("pm") && Integer.parseInt(events_for_notification.getString(4)) == 12)
                    {
                        hourForAlarm = "12";
                        if(events_for_notification.getString(5).length() == 1)
                            minForAlarm = "0" + events_for_notification.getString(5);
                        else
                            minForAlarm = events_for_notification.getString(5);
                    }

                    else if (format_to_convert.equals("pm"))
                    {
                        int a = Integer.parseInt(events_for_notification.getString(4)) + 12;
                        hourForAlarm = Integer.toString(a);
                        if(events_for_notification.getString(5).length() == 1)
                            minForAlarm = "0" + events_for_notification.getString(5);
                        else
                            minForAlarm = events_for_notification.getString(5);
                    }

                    else if(format_to_convert.equals("am"))
                    {
                        if(events_for_notification.getString(4).length() == 1)
                            hourForAlarm = "0" + events_for_notification.getString(4);
                        else
                            hourForAlarm = events_for_notification.getString(4);
                        if(events_for_notification.getString(5).length() == 1)
                            minForAlarm = "0" + events_for_notification.getString(5);
                        else
                            minForAlarm = events_for_notification.getString(5);
                    }
                }
            }
        }
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
                    db.deleteDuplicateRows();
                    if(snapshot.exists())
                    {
                        for (DataSnapshot snapshot1:snapshot.getChildren())
                        {
                            FetchFromFIrebase f1 = snapshot1.getValue(FetchFromFIrebase.class);
                            db.insertFireBaseDataInSQ(f1.getTitle(), f1.getDay(), f1.getMonth(), f1.getYear(), f1.getMinute(), f1.getHour(), f1.getAMorPM(), f1.getEvent(), f1.getID());
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

    public void gotoSHowEvents()
    {
        Intent intent = new Intent(Calender.this, events_list.class);
        intent.putExtra("day_value", dayCa);
        intent.putExtra("month_value", monthCa);
        intent.putExtra("year_value", yearCa);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}