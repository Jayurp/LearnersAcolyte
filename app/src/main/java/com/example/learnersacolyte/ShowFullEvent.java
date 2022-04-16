package com.example.learnersacolyte;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.FirebaseDatabase;

public class ShowFullEvent extends AppCompatActivity {

    TextView Title, Date, Time, Description;
    DbHelper db =  new DbHelper(this);
    String title, date, time, event, id;
    Button cancel, edit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_full_event);

        Intent intent = new Intent();
        id = getIntent().getStringExtra("ID");

        Title = findViewById(R.id.title_textview);
        Date = findViewById(R.id.date_textview);
        Time = findViewById(R.id.time_textview);
        Description = findViewById(R.id.description_textview);
        cancel = findViewById(R.id.cancel_gotoevents);
        edit = findViewById(R.id.editevent_button);
        delete = findViewById(R.id.delete_button);

        Cursor cursor = db.FetchFullEvent(id);

        while(cursor.moveToNext())
        {
            title = cursor.getString(0);
            date = "Date : "+cursor.getString(1)+"/"+cursor.getString(2)+"/"+cursor.getString(3);
            time = "Time : "+cursor.getString(4)+":"+cursor.getString(5)+" "+cursor.getString(6);
            event = cursor.getString(7);
        }

        Title.setText(title);
        Date.setText(date);
        Time.setText(time);
        Description.setText(event);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoEventList();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoEditNotes();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertForDelete();
            }
        });
    }

    public void gotoEventList()
    {
        Intent intent = new Intent(ShowFullEvent.this, Calender.class);
        startActivity(intent);
    }

    public void gotoEditNotes()
    {
        Intent intent = new Intent(ShowFullEvent.this, EditEvent.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void alertForDelete()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure you want to delete this event?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteEvent();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ShowFullEvent.this, "Event is not deleted.", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }

    public void deleteEvent()
    {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ShowFullEvent.this);
        String GoogleID = acct.getId();
        FirebaseDatabase.getInstance().getReference().child(GoogleID).child(id).removeValue();
        Toast.makeText(ShowFullEvent.this, "Event deleted.", Toast.LENGTH_SHORT).show();
        gotoEventList();
    }
}