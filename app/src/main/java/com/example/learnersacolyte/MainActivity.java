package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button next;
    DbHelper myDb = new DbHelper(this);
    EditText name,enr,sem,dept,inst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();



        next = findViewById(R.id.button);
        name = findViewById(R.id.name);
        enr = findViewById(R.id.enr);
        sem = findViewById(R.id.sem);
        dept = findViewById(R.id.dept);
        inst = findViewById(R.id.inst);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dname = name.getText().toString();
                String denr = enr.getText().toString();
                String dsem = sem.getText().toString();
                String ddept = dept.getText().toString();
                String dinst = inst.getText().toString();

                int enr_value = Integer.parseInt(denr);
                int sem_value = Integer.parseInt(dsem);

                //myDb.insertUserDetails(dname,enr_value,sem_value,ddept,dinst);
                start();
            }
        });
    }

    public void start()
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}