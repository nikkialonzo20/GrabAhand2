package com.example.nikkialonzo.grabahand;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

/**
 * Created by gerard on 2/20/2017.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private SupportMapFragment mapFragment;

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

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SWU = new LatLng(10.3020, 123.8918);
        googleMap.addMarker(new MarkerOptions().position(SWU)
                .title("Southwestern University"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SWU, 15.00f));

        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        String name = "Name: John Doe";
        String number = "Phone: 0943 943 0943";
        String email = "Email: john@doe.com";
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
}
