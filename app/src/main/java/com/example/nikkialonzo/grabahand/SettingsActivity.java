package com.example.nikkialonzo.grabahand;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class SettingsActivity extends AppCompatActivity {


    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context = getApplicationContext();


        // Fields for the user
        final EditText name = (EditText) findViewById(R.id.edtName);
        final EditText phone = (EditText) findViewById(R.id.edtPhone);
        final EditText email = (EditText) findViewById(R.id.edtEmail);
        final EditText address = (EditText) findViewById(R.id.edtAddress);

        // Fields for user's contact person
        final EditText cpName = (EditText) findViewById(R.id.edtContactPersonName);
        final EditText cpPhone = (EditText) findViewById(R.id.edtContactPersonPhone);
        final EditText cpEmail = (EditText) findViewById(R.id.edtContactPersonEmail);
        final EditText cpAddress = (EditText) findViewById(R.id.edtContactPersonAddress);



        Button saveSettings = (Button) findViewById(R.id.btnSaveSettings);
        saveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("SIGN_UP", true);

                editor.putString("NAME", name.getText().toString());
                editor.putString("PHONE", phone.getText().toString());
                editor.putString("EMAIL", email.getText().toString());
                editor.putString("ADDRESS", address.getText().toString());


                editor.putString("CP_NAME", cpName.getText().toString());
                editor.putString("CP_PHONE", cpPhone.getText().toString());
                editor.putString("CP_EMAIL", cpEmail.getText().toString());
                editor.putString("CP_ADDRESS", cpAddress.getText().toString());

                editor.apply();


                Intent Buttons = new Intent(v.getContext(), Buttons.class);
                startActivityForResult(Buttons , 0);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateSettingsFields();
    }

    private void populateSettingsFields() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        // Fields for the user
        EditText name = (EditText) findViewById(R.id.edtName);
        EditText phone = (EditText) findViewById(R.id.edtPhone);
        EditText email = (EditText) findViewById(R.id.edtEmail);
        EditText address = (EditText) findViewById(R.id.edtAddress);

        // Fields for user's contact person
        EditText cpName = (EditText) findViewById(R.id.edtContactPersonName);
        EditText cpPhone = (EditText) findViewById(R.id.edtContactPersonPhone);
        EditText cpEmail = (EditText) findViewById(R.id.edtContactPersonEmail);
        EditText cpAddress = (EditText) findViewById(R.id.edtContactPersonAddress);

        name.setText(sharedPreferences.getString("NAME", ""));
        phone.setText(sharedPreferences.getString("PHONE", ""));
        email.setText(sharedPreferences.getString("EMAIL", ""));
        address.setText(sharedPreferences.getString("ADDRESS", ""));
        cpName.setText(sharedPreferences.getString("CP_NAME", ""));
        cpPhone.setText(sharedPreferences.getString("CP_PHONE", ""));
        cpEmail.setText(sharedPreferences.getString("CP_EMAIL", ""));
        cpAddress.setText(sharedPreferences.getString("CP_ADDRESS", ""));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(SettingsActivity.this, Buttons.class);
        startActivity(intent);
        finish();
    }
}
