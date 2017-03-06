package com.example.nikkialonzo.grabahand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Buttons extends AppCompatActivity {


    private boolean doubleBackToExitPressedOnce = false;
    private GrabEndpoint apiService;
    private int userId;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        apiService = new RestClient().getApiService();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Buttons.this);
        userId = sharedPreferences.getInt("USER_ID", 0);
        address = sharedPreferences.getString("CP_ADDRESS", "");

        Button immediateHospital = (Button) findViewById(R.id.btnImmediateHospital);
        immediateHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        Buttons.this);
                alertDialogBuilder.setTitle("Hospital Request Sent!");
                alertDialogBuilder.setMessage("A request has been sent to the nearest hospital.")
                        .setCancelable(false)
                        .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();

                JobInfo jobInfo = new JobInfo(userId, 1,10.3040, 123.8895 ,address);
                Call<SubmitJobResult> call = apiService.registerJob(jobInfo);
                call.enqueue(new Callback<SubmitJobResult>() {
                    @Override
                    public void onResponse(Call<SubmitJobResult> call, Response<SubmitJobResult> response) {
                        SubmitJobResult submitJobResult = response.body();
                        try {
                            if (submitJobResult.getSuccess() == 1) {
                                Toast.makeText(Buttons.this, "Request created.",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitJobResult> call, Throwable t) {
                        Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        Button specificHospital = (Button) findViewById(R.id.btnSpecificHospital);
        specificHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buttons.this, HospitalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button immediateFire = (Button) findViewById(R.id.btnImmediateFire);
        immediateFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        Buttons.this);

                alertDialogBuilder.setTitle("Fireman Request Sent!");
                alertDialogBuilder.setMessage("A request has been sent to the nearest fire station.")
                        .setCancelable(false)
                        .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                JobInfo jobInfo = new JobInfo(userId, 2,10.3040, 123.8895 ,address);
                Call<SubmitJobResult> call = apiService.registerJob(jobInfo);
                call.enqueue(new Callback<SubmitJobResult>() {
                    @Override
                    public void onResponse(Call<SubmitJobResult> call, Response<SubmitJobResult> response) {
                        SubmitJobResult submitJobResult = response.body();
                        try {
                            if (submitJobResult.getSuccess() == 1) {
                                Toast.makeText(Buttons.this, "Request created.",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitJobResult> call, Throwable t) {
                        Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button specificFire = (Button) findViewById(R.id.btnSpecificFire);
        specificFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buttons.this, FireActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Button immediatePolice = (Button) findViewById(R.id.btnImmediatePolice);
        immediatePolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        Buttons.this);

                alertDialogBuilder.setTitle("Police Request Sent!");
                alertDialogBuilder.setMessage("A request has been sent to the nearest police station.")
                        .setCancelable(false)
                        .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                JobInfo jobInfo = new JobInfo(userId, 3,10.3040, 123.8895 ,address);
                Call<SubmitJobResult> call = apiService.registerJob(jobInfo);
                call.enqueue(new Callback<SubmitJobResult>() {
                    @Override
                    public void onResponse(Call<SubmitJobResult> call, Response<SubmitJobResult> response) {
                        SubmitJobResult submitJobResult = response.body();
                        try {
                            if (submitJobResult.getSuccess() == 1) {
                                Toast.makeText(Buttons.this, "Request created.",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitJobResult> call, Throwable t) {
                        Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button specificPolice = (Button) findViewById(R.id.btnSpecificPolice);
        specificPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buttons.this, PoliceActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button immediateMechanic = (Button) findViewById(R.id.btnImmediateMechanic);
        immediateMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        Buttons.this);

                alertDialogBuilder.setTitle("Mechanic Request Sent!");
                alertDialogBuilder.setMessage("A request has been sent to the nearest mechanic.")
                        .setCancelable(false)
                        .setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                JobInfo jobInfo = new JobInfo(userId, 4,10.3040, 123.8895 ,address);
                Call<SubmitJobResult> call = apiService.registerJob(jobInfo);
                call.enqueue(new Callback<SubmitJobResult>() {
                    @Override
                    public void onResponse(Call<SubmitJobResult> call, Response<SubmitJobResult> response) {
                        SubmitJobResult submitJobResult = response.body();
                        try {
                            if (submitJobResult.getSuccess() == 1) {
                                Toast.makeText(Buttons.this, "Request created.",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitJobResult> call, Throwable t) {
                        Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button specificMechanic = (Button) findViewById(R.id.btnSpecificMechanic);
        specificMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buttons.this, MechanicActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        // Inflate options
        inflater.inflate(R.menu.buttons_menu, menu);
        return true;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case (R.id.settings):
                intent.setClass(Buttons.this, SettingsActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
