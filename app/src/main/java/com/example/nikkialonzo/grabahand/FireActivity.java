package com.example.nikkialonzo.grabahand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FireActivity extends AppCompatActivity {
    private Spinner list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = (Spinner) findViewById(R.id.dropStations);
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.fire_list, R.layout.my_spinner_dropdown);
        list.setAdapter(countryAdapter);


        Button emergencyButton = (Button) findViewById(R.id.btnImmediate);
        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        FireActivity.this);

                alertDialogBuilder.setTitle("Request Sent!");
                alertDialogBuilder.setMessage("A request has been sent to the nearest fire station.")
                        .setCancelable(false)
                        .setNeutralButton("Okay",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        Button specificStation = (Button) findViewById(R.id.btnSpecific);
        specificStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        FireActivity.this);

                alertDialogBuilder.setTitle("Request Sent!");
                alertDialogBuilder.setMessage("A request has been sent to " + list.getSelectedItem().toString())
                        .setCancelable(false)
                        .setNeutralButton("Okay",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent Buttons = new Intent(getApplicationContext(), Buttons.class);
        startActivity(Buttons);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
