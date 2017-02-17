package com.example.nikkialonzo.grabahand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class hButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_activity);

        ImageButton imageButtonh = (ImageButton) findViewById(R.id.imageButtonh);
        imageButtonh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Buttons = new Intent(v.getContext(), Buttons.class);
                startActivityForResult(Buttons , 0);
            }
        });
    }
}
