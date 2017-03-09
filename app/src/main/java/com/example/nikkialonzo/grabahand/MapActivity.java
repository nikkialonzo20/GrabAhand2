package com.example.nikkialonzo.grabahand;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gerard on 2/20/2017.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private SupportMapFragment mapFragment;
    private GrabEndpoint apiService;
    private SharedPreferences sharedPreferences;
    private boolean doubleBackToExitPressedOnce = false;
    private GoogleMap googleMap;
    private ArrayList<Job> jobs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_map);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        apiService = new RestClient().getApiService();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MapActivity.this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        retrieveJobs();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
       /* LatLng SWU = new LatLng(10.3020, 123.8918);
        googleMap.addMarker(new MarkerOptions().position(SWU)
                .title("Southwestern University"));*/
        this.googleMap = googleMap;
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(10.3157, 123.8854), 15.00f));
        this.googleMap.setOnMarkerClickListener(this);

        /*LatLng sunrise = new LatLng(10.2778832, 123.8530936);

        this.googleMap.addMarker(new MarkerOptions().position(sunrise).title("Sunrise"));*/
        retrieveJobs();
        //LatLng sunrise = new LatLng(10.2778832,123.8530936);
        //this.googleMap.addMarker(new MarkerOptions().position(sunrise).title("Sunrise"));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        String cpName = "";
        String cpNumber = "";
        String cpAddress = "";
        String phoneHolderName = "";
        String phoneHolderCP = "";

        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            if (job.getId() == Integer.valueOf(marker.getTitle())) {
                phoneHolderName = job.getName();
                phoneHolderCP = job.getPhone();
                cpName = job.getCpName();
                cpNumber = job.getCpPhone();
                cpAddress = job.getCpAddress();
            }
        }

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final int id = Integer.valueOf(marker.getTitle());

        dialog.setTitle("EMERGENCY");
        dialog.setMessage("Name: " + phoneHolderName +
                        "\nPhone: " + phoneHolderCP +
                        "\n\nPLEASE CONTACT\n" +
                        "Contact Person: " + cpName +
                        "\n" + "Number: " + cpNumber +
                        "\n" + "Address: " + cpAddress)
                .setNeutralButton("RESPOND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        acceptJob(id);

                        final AlertDialog.Builder dialog = new AlertDialog.Builder(MapActivity.this);
                        final int id = Integer.valueOf(marker.getTitle());

                        dialog.setTitle("EMERGENCY ASSIGNED");
                        dialog.setMessage("You are currently assigned to an emergency. Click done ONLY if you are finished.")
                                .setNeutralButton("DONE", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finishJob(id);
                                    }
                                }).setCancelable(false);

                        AlertDialog dialog1 = dialog.create();


                        dialog1.show();
                    }
                });

        AlertDialog dialog1 = dialog.create();


        dialog1.show();
        return true;


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

    //to accept request

    private void acceptJob(int id) {
        Call<AcceptJobResult> call = apiService.acceptJob(id);
        final int reqId = id;
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        call.enqueue(new Callback<AcceptJobResult>() {
            @Override
            public void onResponse(Call<AcceptJobResult> call, Response<AcceptJobResult> response) {
                AcceptJobResult acceptJobResult = response.body();
                try {
                    if (acceptJobResult.getSuccess() == 1) {
                        //job is accepted and the admin is now heading to
                        editor.putInt("JOB_HANDLED", reqId);
                        editor.apply();
                        Toast.makeText(getApplicationContext(), "A feedback has been sent to the user that help is on the way.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MapActivity.this, "Please try again.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MapActivity.this, "Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AcceptJobResult> call, Throwable t) {
                Toast.makeText(MapActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //to click that the rquest is finish request
    private void finishJob(int id) {
        Call<FinishJobResult> call = apiService.finishJob(id);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        call.enqueue(new Callback<FinishJobResult>() {
            @Override
            public void onResponse(Call<FinishJobResult> call, Response<FinishJobResult> response) {
                FinishJobResult finishJobResult = response.body();
                try {
                    if (finishJobResult.getSuccess() == 1) {
                        //job is now status 2 and it is finished
                        editor.putInt("JOB_HANDLED", 0);
                        editor.apply();
                        Toast.makeText(MapActivity.this, "Done handling request.", Toast.LENGTH_SHORT).show();
                        recreate();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<FinishJobResult> call, Throwable t) {
                Toast.makeText(MapActivity.this, "lease try again", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void retrieveJobs() {

        final int jobId = sharedPreferences.getInt("JOB_ID", 0);
        Call<RetrieveJobResults> call = apiService.retrieveJobs(jobId);
        call.enqueue(new Callback<RetrieveJobResults>() {
            @Override
            public void onResponse(Call<RetrieveJobResults> call, Response<RetrieveJobResults> response) {
                RetrieveJobResults retrieveJobResults = response.body();
                try {
                    if (retrieveJobResults.getSuccess() == 1) {
                        Toast.makeText(MapActivity.this, "Available requests.", Toast.LENGTH_SHORT).show();
                        jobs = retrieveJobResults.getJobs(); //jobs ang mga available na requests.

                        Log.e("job id: ", jobId + "");
                        Log.e("jobs: ", jobs.size() + "");
                        for (Job job : jobs) {
                            Log.e("job id", job.getId() + " lat: " + job.getLat() + " long: " + job.getLon());

                            LatLng coords = new LatLng(job.getLon(), job.getLat());
                            googleMap.addMarker(new MarkerOptions().position(coords)).setTitle(String.valueOf(job.getId()));
                            Log.e("added", coords.toString());
                        }


                    } else {
                        Toast.makeText(MapActivity.this, "No requests found.", Toast.LENGTH_SHORT).show();
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RetrieveJobResults> call, Throwable t) {
                Toast.makeText(MapActivity.this, "Bad connection.", Toast.LENGTH_SHORT).show();
                String message = t.getMessage();
                Log.d("failure", message);
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

    private void registerPingReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.my.app.onMessageReceived");
        registerReceiver(pingReceiver, intentFilter);
        Toast.makeText(getApplicationContext(), "registered", Toast.LENGTH_SHORT).show();

    }

    public BroadcastReceiver pingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //do something after ping
            Toast.makeText(context, "received", Toast.LENGTH_SHORT).show();
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            if (sp.getInt("JOB_HANDLED", 0) == 0)
                retrieveJobs();

            recreate();
        }
    };
}
