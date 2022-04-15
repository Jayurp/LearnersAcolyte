package com.example.learnersacolyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Enotes extends AppCompatActivity {

    Spinner dept_spinner, sem_spinner, subject_spinner;
    ListView list;
    ListView NotesList;

    String[]  chapters = {"Chapter 1","Chapter 2","Chapter 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enotes);

        dept_spinner = findViewById(R.id.dept_spinner);
        sem_spinner = findViewById(R.id.sem_spinner);
        subject_spinner = findViewById(R.id.subject_spinner);
        NotesList = findViewById(R.id.NotesList);


        ArrayAdapter<CharSequence> dept_adpter = ArrayAdapter.createFromResource(this, R.array.dept_names, android.R.layout.simple_spinner_item);
        dept_adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept_spinner.setAdapter(dept_adpter);

        ArrayAdapter<CharSequence> sem_adpter = ArrayAdapter.createFromResource(this, R.array.sem_names, android.R.layout.simple_spinner_item);
        sem_adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem_spinner.setAdapter(sem_adpter);

        ArrayAdapter<CharSequence> first_sem_sub = ArrayAdapter.createFromResource(this, R.array.first_sem_sub, android.R.layout.simple_spinner_item);
        first_sem_sub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> second_sem_sub = ArrayAdapter.createFromResource(this, R.array.second_sem_sub, android.R.layout.simple_spinner_item);
        second_sem_sub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> third_sem_sub = ArrayAdapter.createFromResource(this, R.array.third_sem_sub, android.R.layout.simple_spinner_item);
        third_sem_sub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> fourth_sem_sub = ArrayAdapter.createFromResource(this, R.array.fourth_sem_sub, android.R.layout.simple_spinner_item);
        fourth_sem_sub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> fifth_sem_sub = ArrayAdapter.createFromResource(this, R.array.fifth_sem_sub, android.R.layout.simple_spinner_item);
        fifth_sem_sub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> sixth_sem_sub = ArrayAdapter.createFromResource(this, R.array.sixth_sem_sub, android.R.layout.simple_spinner_item);
        sixth_sem_sub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> default_sub = ArrayAdapter.createFromResource(this, R.array.default_subject, android.R.layout.simple_spinner_item);
        default_sub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject_spinner.setAdapter(default_sub);

        sem_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    subject_spinner.setAdapter(first_sem_sub);
                    subject_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                       @Override
                       public void onItemSelected(AdapterView<?> parent, View view, int sub, long id) {
                           if(sub == 1 && position == 1) {

                               ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Enotes.this, android.R.layout.simple_list_item_1, chapters);
                               NotesList.setAdapter(arrayAdapter);

                               NotesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                   @Override
                                   public void onItemClick(AdapterView<?> parent, View view, int firstsem_sub, long id) {
                                       if(firstsem_sub == 0)
                                           Goto1();
                                       else if(firstsem_sub == 1)
                                           Goto2();
                                   }
                               });

                               };
                           }
                       public void onNothingSelected(AdapterView<?> parent) {
                       }
                   });
                }
                else if (position == 2)
                    subject_spinner.setAdapter(second_sem_sub);
                else if (position == 3)
                    subject_spinner.setAdapter(third_sem_sub);
                else if (position == 4)
                    subject_spinner.setAdapter(fourth_sem_sub);
                else if (position == 5)
                    subject_spinner.setAdapter(fifth_sem_sub);
                else if (position == 6)
                    subject_spinner.setAdapter(sixth_sem_sub);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void Goto1()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1Cxqpt1cZqN0bT7AqF8w2avNoOKnJylqR/view?usp=sharing"));
        startActivity(intent);
    }

    public void Goto2()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/0B47_st74UnYkc3RhcnRlcl9maWxl/view?usp=sharing&resourcekey=0-siV4aueIi74LM3Mnpxp6zg"));
        startActivity(intent);
    }
}