package com.example.nikkialonzo.grabahand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalActivity extends AppCompatActivity {

    // Declare properties
    private Spinner list;
    private GrabEndpoint apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(HospitalActivity.this);
        final int userId = sharedPreferences.getInt("USER_ID", 0);
        final String address = sharedPreferences.getString("CP_ADDRESS", "");

        // Bind properties to their view
        list = (Spinner) findViewById(R.id.dropStations);
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.hospital_list, R.layout.my_spinner_dropdown);
        list.setAdapter(countryAdapter);

        apiService = new RestClient().getApiService();

        Button specificStation = (Button) findViewById(R.id.btnSpecific);
        specificStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        HospitalActivity.this);

                alertDialogBuilder.setTitle("Request Sent!");
                alertDialogBuilder.setMessage("A request has been sent to " + list.getSelectedItem().toString())
                        .setCancelable(false)
                        .setNeutralButton("Okay",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                startActivity(new Intent(HospitalActivity.this, Buttons.class));
                                finish();
                            }
                        });

                int jobId;
                switch (list.getSelectedItemPosition()){
                    case 0:     // Chong Hua
                        jobId = 100;
                            break;
                    case 1:     // Philippine Red Cross
                        jobId = 101;
                            break;
                    case 2:     // Sacred Heart Hospital
                        jobId = 102;
                            break;
                    case 3:     // Perpetual Succour Hospital
                        jobId = 103;
                            break;
                    case 4:     // Cebu Doctors University Hospital
                        jobId = 104;
                            break;
                    case 5:     // Cebu Velez General Hospital
                        jobId = 105;
                            break;
                    case 6:     // Cebu City Medical Center
                        jobId = 106;
                            break;
                    case 7:     // Vicente Sotto Medical Center-Cebu City
                        jobId = 107;
                            break;
                    case 8:     // Visayas Community Medical Center
                        jobId = 108;
                            break;
                    case 9:     // Cebu Velez General Hospital
                        jobId = 109;
                            break;

                    default: jobId = 0;
                        break;

                }

                JobInfo jobInfo = new JobInfo(userId, jobId, 10.3040, 123.8895 ,address);
                Call<SubmitJobResult> call = apiService.registerJob(jobInfo);
                call.enqueue(new Callback<SubmitJobResult>() {
                    @Override
                    public void onResponse(Call<SubmitJobResult> call, Response<SubmitJobResult> response) {
                        SubmitJobResult submitJobResult = response.body();
                        try {
                            if (submitJobResult.getSuccess() == 1) {
                                Toast.makeText(HospitalActivity.this, "Request created. Please take note of your REQUEST ID: " + submitJobResult.getJobRequested().getId() ,Toast.LENGTH_SHORT).show();
                                Gson gson = new Gson();
                                String jobReq = sharedPreferences.getString("JOBREQ", "FALSE");
                                SharedPreferences.Editor editor = sharedPreferences.edit();
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
                                Toast.makeText(HospitalActivity.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitJobResult> call, Throwable t) {
                        Toast.makeText(HospitalActivity.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
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
