package com.example.nikkialonzo.grabahand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class mButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_button);

        ImageButton imageButtonm = (ImageButton) findViewById(R.id.imageButtonm);
        imageButtonm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Buttons = new Intent(v.getContext(), Buttons.class);
                startActivityForResult(Buttons , 0);
            }
        });
    }
}
