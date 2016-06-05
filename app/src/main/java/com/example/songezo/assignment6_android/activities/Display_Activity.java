package com.example.songezo.assignment6_android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songezo.assignment6_android.R;
import com.example.songezo.assignment6_android.activities.DB_View_Activity;
import com.example.songezo.assignment6_android.domain.Tournaments;
import com.example.songezo.assignment6_android.repository.Impl.Tournaments_Repository_impl;

public class Display_Activity extends AppCompatActivity {

    Tournaments_Repository_impl tournamentsRepositoryImpl;
    Tournaments tournaments;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_);
        String entryOne = getIntent().getStringExtra("Entry1");
        String entryTwo = getIntent().getStringExtra("Entry2");
        String entryThree = getIntent().getStringExtra("Entry3");

         textView1 = (TextView) findViewById(R.id.TVentry1);
         textView2 = (TextView) findViewById(R.id.TVentry2);
         textView3 = (TextView) findViewById(R.id.TVentry3);
         submit = (Button) findViewById(R.id.bSubmit);

        textView1.setText(entryOne);
        textView2.setText(entryTwo);
        textView3.setText(entryThree);

        tournamentsRepositoryImpl = new Tournaments_Repository_impl(this, null, null, 1, null);

        //AddData();
    }

    public void onButtonClick(View view){
        if (view.getId() == R.id.bSubmit) {
            Intent i = new Intent(Display_Activity.this, DB_View_Activity.class);

//            tournaments = new Tournaments();
//            tournamentsRepositoryImpl.save(tournaments);

            startActivity(i);
        }
    }

//    public void AddData()
//    {
//        assert submit != null;
//        submit.setOnClickListener(
//                new View.OnClickListener()
//                {
//                    Intent intent = new Intent (getApplicationContext(), DB_View_Activity.class);
//                    @Override
//                    public void onClick(View v) {
//                        Tournaments isInserted = tournamentsRepositoryImpl.save(tournaments);
//                        if(isInserted == tournaments)
//                        {
//                            Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
//                            startActivity(intent);
//                        }
//                        else
//                            Toast.makeText(getApplicationContext(), "Not Inserted", Toast.LENGTH_LONG).show();
//
//
//                    }
//                }
//        );
//    }

}
