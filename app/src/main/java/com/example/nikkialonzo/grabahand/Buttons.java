package com.example.nikkialonzo.grabahand;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Buttons extends AppCompatActivity {


    // Declare properties
    Button immediateHospital;
    Button specificHospital;
    Button immediateFire;
    Button specificFire;
    Button immediatePolice;
    Button specificPolice;
    Button immediateMechanic;
    Button specificMechanic;

    private boolean doubleBackToExitPressedOnce = false;
    private GrabEndpoint apiService;
    private int userId;
    private String address;
    private String jobReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        apiService = new RestClient().getApiService();
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Buttons.this);
        userId = sharedPreferences.getInt("USER_ID", 0);
        address = sharedPreferences.getString("CP_ADDRESS", "");
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        // Bind properties to their views and set their on click listeners
        immediateHospital = (Button) findViewById(R.id.btnImmediateHospital);
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
                                Gson gson = new Gson();
                                jobReq = sharedPreferences.getString("JOBREQ", "FALSE");
                                if(jobReq == "FALSE"){
                                    ArrayList<JobRequested> jobRequestedArrayList = new ArrayList<>();
                                    jobRequestedArrayList.add(submitJobResult.getJobRequested());
                                    String json = gson.toJson(jobRequestedArrayList);
                                    editor.putString("JOBREQ", json);
                                    editor.commit();
                                }else{
                                    Type type = new TypeToken<ArrayList<JobRequested>>(){}.getType();
                                    ArrayList<JobRequested> jobRequestedArrayList = gson.fromJson(jobReq, type);
                                    jobRequestedArrayList.add(submitJobResult.getJobRequested());
                                    String json = gson.toJson(jobRequestedArrayList);
                                    editor.putString("JOBREQ", json);
                                    editor.commit();
                                }

                                Toast.makeText(Buttons.this, "Request created. Please take note of your REQUEST ID: "+ submitJobResult.getJobRequested().getId(),Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitJobResult> call, Throwable t) {
                        Toast.makeText(Buttons.this, "Creating reqaw awauest failed.",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        specificHospital = (Button) findViewById(R.id.btnSpecificHospital);
        specificHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buttons.this, HospitalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        immediateFire = (Button) findViewById(R.id.btnImmediateFire);
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
                                Toast.makeText(Buttons.this, "Request created. Please take note of your REQUEST ID: "+ submitJobResult.getJobRequested().getId(),Toast.LENGTH_SHORT).show();
                                Gson gson = new Gson();
                                jobReq = sharedPreferences.getString("JOBREQ", "FALSE");
                                if(jobReq == "FALSE"){
                                    ArrayList<JobRequested> jobRequestedArrayList = new ArrayList<>();
                                    jobRequestedArrayList.add(submitJobResult.getJobRequested());
                                    String json = gson.toJson(jobRequestedArrayList);
                                    editor.putString("JOBREQ", json);
                                    editor.commit();
                                }else{
                                    Type type = new TypeToken<ArrayList<JobRequested>>(){}.getType();
                                    ArrayList<JobRequested> jobRequestedArrayList = gson.fromJson(jobReq, type);
                                    jobRequestedArrayList.add(submitJobResult.getJobRequested());
                                    String json = gson.toJson(jobRequestedArrayList);
                                    editor.putString("JOBREQ", json);
                                    editor.commit();
                                }
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

        specificFire = (Button) findViewById(R.id.btnSpecificFire);
        specificFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buttons.this, FireActivity.class);
                startActivity(intent);
                finish();
            }
        });


        immediatePolice = (Button) findViewById(R.id.btnImmediatePolice);
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
                                Toast.makeText(Buttons.this, "Request created. Please take note of your REQUEST ID: " + submitJobResult.getJobRequested().getId() ,Toast.LENGTH_SHORT).show();
                                Gson gson = new Gson();
                                jobReq = sharedPreferences.getString("JOBREQ", "FALSE");
                                if(jobReq == "FALSE"){
                                    ArrayList<JobRequested> jobRequestedArrayList = new ArrayList<>();
                                    jobRequestedArrayList.add(submitJobResult.getJobRequested());
                                    String json = gson.toJson(jobRequestedArrayList);
                                    editor.putString("JOBREQ", json);
                                    editor.commit();
                                }else{
                                    Type type = new TypeToken<ArrayList<JobRequested>>(){}.getType();
                                    ArrayList<JobRequested> jobRequestedArrayList = gson.fromJson(jobReq, type);
                                    jobRequestedArrayList.add(submitJobResult.getJobRequested());
                                    String json = gson.toJson(jobRequestedArrayList);
                                    editor.putString("JOBREQ", json);
                                    editor.commit();
                                }
                            }else{
                                Toast.makeText(Buttons.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitJobResult> call, Throwable t) {
                        Toast.makeText(Buttons.this, "Creating request awwa failed.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        specificPolice = (Button) findViewById(R.id.btnSpecificPolice);
        specificPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buttons.this, PoliceActivity.class);
                startActivity(intent);
                finish();
            }
        });

        immediateMechanic = (Button) findViewById(R.id.btnImmediateMechanic);
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
                                Toast.makeText(Buttons.this, "Request created. Please take note of your REQUEST ID: " + submitJobResult.getJobRequested().getId(),Toast.LENGTH_SHORT).show();
                                Gson gson = new Gson();
                                jobReq = sharedPreferences.getString("JOBREQ", "FALSE");
                                if(jobReq == "FALSE"){
                                    ArrayList<JobRequested> jobRequestedArrayList = new ArrayList<>();
                                    jobRequestedArrayList.add(submitJobResult.getJobRequested());
                                    String json = gson.toJson(jobRequestedArrayList);
                                    editor.putString("JOBREQ", json);
                                    editor.commit();
                                }else{
                                    Type type = new TypeToken<ArrayList<JobRequested>>(){}.getType();
                                    ArrayList<JobRequested> jobRequestedArrayList = gson.fromJson(jobReq, type);
                                    jobRequestedArrayList.add(submitJobResult.getJobRequested());
                                    String json = gson.toJson(jobRequestedArrayList);
                                    editor.putString("JOBREQ", json);
                                    editor.commit();
                                }
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

        specificMechanic = (Button) findViewById(R.id.btnSpecificMechanic);
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
    protected void onResume() {
        super.onResume();
        registerPingReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(pingReceiver);
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

    private void registerPingReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.my.app.onMessageReceived");
        registerReceiver(pingReceiver, intentFilter);
    }

    public BroadcastReceiver pingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
            getStatusJobs(userId);
        }
    };

    private void getStatusJobs(int id){
        Call<JobStatusResult> call = apiService.checkStatus(id);
        call.enqueue(new Callback<JobStatusResult>() {
            @Override
            public void onResponse(Call<JobStatusResult> call, Response<JobStatusResult> response) {
                JobStatusResult jobStatusResult = response.body();
                try {
                    if (jobStatusResult.getSuccess() == 1) {
                        showUpdate(jobStatusResult.getJobRequested());
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<JobStatusResult> call, Throwable t) {
                Toast.makeText(Buttons.this, "Please try again",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showUpdate(ArrayList<JobRequested> jobRequesteds) {
        Gson gson = new Gson();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Buttons.this);
        SharedPreferences.Editor editor = sp.edit();
        jobReq = sp.getString("JOBREQ", "FALSE");
        Type type = new TypeToken<ArrayList<JobRequested>>(){}.getType();
        ArrayList<JobRequested> jobRequestedArrayList = gson.fromJson(jobReq, type);


       AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        for(int i=0; i< jobRequestedArrayList.size(); i++){

            JobRequested jobRequested1, jobRequested2;
            jobRequested1 = jobRequestedArrayList.get(i);
            jobRequested2 = jobRequesteds.get(i);

            if(jobRequested1.getStatus() != jobRequested2.getStatus()){

                jobRequestedArrayList.get(i).setStatus(jobRequested2.getStatus());

                if(jobRequested2.getStatus() == 1){
                    dialog.setTitle("UPDATE");
                    dialog.setMessage("Help is on its way for Request ID: "+ jobRequested2.getId() + "\n"
                            + "Name: " + jobRequested2.getName() + "\n"
                            + "Phone: " + jobRequested2.getPhone() + "\n"
                            + "Address: " + jobRequested2.getAddress() + "\n"
                    );
                    AlertDialog dialog1 = dialog.create();
                    dialog1.show();

                }else if(jobRequested2.getStatus() == 2){

                    dialog.setTitle("FINISHED");
                    dialog.setMessage("Transaction done for Request ID: "+ jobRequested2.getId() + "\n"
                            + "Name: " + jobRequested2.getName() + "\n"
                            + "Phone: " + jobRequested2.getPhone() + "\n"
                            + "Address: " + jobRequested2.getAddress() + "\n"
                    );
                    AlertDialog dialog1 = dialog.create();
                    dialog1.show();
                }
                break;
            }
        }
        String json = gson.toJson(jobRequestedArrayList);
        editor.putString("JOBREQ", json);
        editor.commit();


    }
}
