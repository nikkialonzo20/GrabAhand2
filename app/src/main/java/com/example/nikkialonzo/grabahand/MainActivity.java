package com.example.nikkialonzo.grabahand;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();


        Button user = (Button) findViewById(R.id.btnUser);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
                boolean signedUp = sp.getBoolean("SIGN_UP", false);
                Intent intent = new Intent();
                if (signedUp) {
                    intent.setClass(MainActivity.this, Buttons.class);
                } else {
                    intent.setClass(MainActivity.this, Introduction.class);
                }
                startActivity(intent);
                finish();
            }
        });


        Button admin = (Button) findViewById(R.id.btnAdmin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
                boolean status = sp.getBoolean("LOGGED_IN_ADMIN", false);
                Intent intent = new Intent();

                if (status) {
                    intent.setClass(MainActivity.this, MapActivity.class);
                } else {
                    intent.setClass(MainActivity.this, AdminLogin.class);
                }
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        boolean login = sp.getBoolean("LOGGED_IN", false);
        boolean status = sp.getBoolean("LOGGED_IN_ADMIN", false);
        boolean buttons = sp.getBoolean("FROM_BUTTONS", false);

        Intent intent = new Intent();
        if (status) {
            intent.setClass(MainActivity.this, MapActivity.class);
            startActivity(intent);
            finish();
        } else if (login) {
            if (buttons) {
                intent.setClass(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            intent.setClass(MainActivity.this, Buttons.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {

        // Check if user has pressed back button once
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        // Sets boolean to true when user presses back button
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        // System waits for 2 seconds before setting boolean value to false again
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
