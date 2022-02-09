package com.example.learnersacolyte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class HomePage extends AppCompatActivity {

    ImageButton enotes;
    ImageButton calender;
    GoogleSignInClient mGoogleSignInClient;
    DbHelper db = new DbHelper(HomePage.this);

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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Sign out");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getTitle().equals("Sign out"))
            signOut();

        return super.onOptionsItemSelected(item);
    }

    private void signOut() {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(HomePage.this, "Signed out", Toast.LENGTH_SHORT).show();
                        db.DeleteTable();
                        GotoLogin();
                    }
                });

    }

    private void GotoLogin()
    {
        Intent intent = new Intent(HomePage.this, GoogleLogin.class);
        startActivity(intent);
    }
}