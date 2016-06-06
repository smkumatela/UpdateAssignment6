package com.example.songezo.assignment6_android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.songezo.assignment6_android.R;

public class DB_View_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db__view_);
    }

    public void onButtonClick(View v){
        if (v.getId() == R.id.bHome){
            Intent i = new Intent(DB_View_Activity.this, MainActivity_Login.class);

            startActivity(i);
        }
    }
}
