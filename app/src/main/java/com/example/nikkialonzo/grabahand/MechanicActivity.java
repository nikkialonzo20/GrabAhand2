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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MechanicActivity extends AppCompatActivity {

    // Declare properties
    private Spinner list;
    private GrabEndpoint apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MechanicActivity.this);
        final int userId = sharedPreferences.getInt("USER_ID", 0);
        final String address = sharedPreferences.getString("CP_ADDRESS", "");

        // Bind properties to their views
        list = (Spinner) findViewById(R.id.dropStations);
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.mechanic_list, R.layout.my_spinner_dropdown);
        list.setAdapter(countryAdapter);
        apiService = new RestClient().getApiService();

        Button specificStation = (Button) findViewById(R.id.btnSpecific);
        specificStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MechanicActivity.this);

                alertDialogBuilder.setTitle("Request Sent!");
                alertDialogBuilder.setMessage("A request has been sent to " + list.getSelectedItem().toString())
                        .setCancelable(false)
                        .setNeutralButton("Okay",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                JobInfo jobInfo = new JobInfo(userId, 4, 10.3040, 123.8895 ,address);
                Call<SubmitJobResult> call = apiService.registerJob(jobInfo);
                call.enqueue(new Callback<SubmitJobResult>() {
                    @Override
                    public void onResponse(Call<SubmitJobResult> call, Response<SubmitJobResult> response) {
                        SubmitJobResult submitJobResult = response.body();
                        try {
                            if (submitJobResult.getSuccess() == 1) {
                                Toast.makeText(MechanicActivity.this, "Request created.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MechanicActivity.this, Buttons.class));
                                finish();
                            }else{
                                Toast.makeText(MechanicActivity.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitJobResult> call, Throwable t) {
                        Toast.makeText(MechanicActivity.this, "Creating request failed.",Toast.LENGTH_SHORT).show();
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
