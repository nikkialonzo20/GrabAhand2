package com.example.nikkialonzo.grabahand;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
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
        final int jobId = sharedPreferences.getInt("JOB_ID", 0);

        Call<RetrieveJobResults> call = apiService.retrieveJobs(jobId);
        call.enqueue(new Callback<RetrieveJobResults>() {
            @Override
            public void onResponse(Call<RetrieveJobResults> call, Response<RetrieveJobResults> response) {
                RetrieveJobResults retrieveJobResults = response.body();
                try {
                    if (retrieveJobResults.getSuccess() == 1) {
                        Toast.makeText(MapActivity.this, "Available requests.",Toast.LENGTH_SHORT).show();
                        ArrayList<Job>  jobs = retrieveJobResults.getJobs(); //jobs ang mga available na requests.
                    }else{
                        Toast.makeText(MapActivity.this, "Retrieve failed.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<RetrieveJobResults> call, Throwable t) {
                Toast.makeText(MapActivity.this, "Retrieve failed.",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SWU = new LatLng(10.3040, 123.8895);
        googleMap.addMarker(new MarkerOptions().position(SWU)
                .title("Southwestern University"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SWU, 15.00f));

        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        String name = "Name: ";
        String number = "Phone: ";
        String email = "Email: ";
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("EMERGENCY");
        dialog.setMessage(name + "\n \n" + number + "\n \n" + email)
                .setNeutralButton("RESPOND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "A feedback has been sent to the user that help is on the way.", Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog dialog1 = dialog.create();


        dialog1.show();
        return true;


    }

    //to accept request

    private void acceptJob(int id){
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

                    }else{
                        Toast.makeText(MapActivity.this, "Please try again.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<AcceptJobResult> call, Throwable t) {
                Toast.makeText(MapActivity.this, "lease try again",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //check status of rquest
    private void getStatusJob(int id){
        Call<JobStatusResult> call = apiService.checkStatus(id);
        call.enqueue(new Callback<JobStatusResult>() {
            @Override
            public void onResponse(Call<JobStatusResult> call, Response<JobStatusResult> response) {
                JobStatusResult jobStatusResult = response.body();
                try {
                    if (jobStatusResult.getSuccess() == 1) {
                        //check status of request, 1 for handled 2 for finished.
                        Toast.makeText(MapActivity.this, String.valueOf(jobStatusResult.getStatus()),Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<JobStatusResult> call, Throwable t) {
                Toast.makeText(MapActivity.this, "lease try again",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //to click that the rquest is finish request

    private void finishJob(int id){
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
                        Toast.makeText(MapActivity.this, "Done handling request.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<FinishJobResult> call, Throwable t) {
                Toast.makeText(MapActivity.this, "lease try again",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
