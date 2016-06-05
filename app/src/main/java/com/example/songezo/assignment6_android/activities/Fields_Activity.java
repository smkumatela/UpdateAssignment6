package com.example.songezo.assignment6_android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.songezo.assignment6_android.R;

public class Fields_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields_);
        String username = getIntent().getStringExtra("Username");

        TextView tv = (TextView) findViewById(R.id.TVusername);
        tv.setText(username);
    }

    public void onButtonClick(View v){
        if (v.getId() == R.id.bView){
            EditText entry1 = (EditText) findViewById(R.id.TFentry1);
            EditText entry2 = (EditText) findViewById(R.id.TFentry2);
            EditText entry3 = (EditText) findViewById(R.id.TFentry3);

            String str1 = entry1.getText().toString();
            String str2 = entry2.getText().toString();
            String str3 = entry3.getText().toString();

            Intent i = new Intent(Fields_Activity.this, Display_Activity.class);
            //Intent i1 = new Intent(Fields_Activity.this, Display_Activity.class);

            i.putExtra("Entry1", str1);
            i.putExtra("Entry2", str2);
            i.putExtra("Entry3", str3);
            startActivity(i);
        }
    }
}
