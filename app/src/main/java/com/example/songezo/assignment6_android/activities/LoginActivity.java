package com.example.songezo.assignment6_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.songezo.assignment6_android.R;

/**
 * Created by Songezo on 2016-05-06.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity__login);
        TextView registerScreen = (TextView) findViewById(R.id.action_settings);

        // Listening to register new account link
        assert registerScreen != null;
        registerScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), MainActivity_Login.class);
                startActivity(i);
            }
        });
    }


}
