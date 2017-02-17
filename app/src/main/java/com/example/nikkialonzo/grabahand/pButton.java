package com.example.nikkialonzo.grabahand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class pButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_button);

        ImageButton imageButtonp = (ImageButton) findViewById(R.id.imageButtonp);
        imageButtonp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Buttons = new Intent(v.getContext(), Buttons.class);
                startActivityForResult(Buttons , 0);
            }
        });
    }
}
