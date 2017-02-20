package com.example.nikkialonzo.grabahand;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Buttons extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        ImageButton btnHospital = (ImageButton) findViewById(R.id.btnHospital);
        btnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hButton = new Intent(v.getContext(), HospitalActivity.class);
                startActivity(hButton);
                finish();
            }
        });

        ImageButton btnFire = (ImageButton) findViewById(R.id.btnFire);
        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fButton = new Intent(v.getContext(), FireActivity.class);
                startActivity(fButton);
                finish();
            }
        });


        ImageButton btnPolice = (ImageButton) findViewById(R.id.btnPolice);
        btnPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pButton = new Intent(v.getContext(), PoliceActivity.class);
                startActivity(pButton);
                finish();
            }
        });

        ImageButton btnMechanic = (ImageButton) findViewById(R.id.btnMechanic);
        btnMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mButton = new Intent(v.getContext(), MechanicActivity.class);
                startActivity(mButton);
                finish();
            }
        });

        ImageButton btnSettings = (ImageButton) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button test = (Button) findViewById(R.id.test);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
