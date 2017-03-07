package com.example.nikkialonzo.grabahand;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class Introduction extends AppCompatActivity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        context = getApplicationContext();


        Button signUp = (Button) findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {

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

            @Override
            public void onClick(View v) {

                if(name.getText().toString().equals("")){
                    name.setError("Please fill up field");
                }
                else if(phone.getText().toString().equals("")){
                    phone.setError("Please fill up field");
                }
                else if(email.getText().toString().equals("")){
                    email.setError("Please fill up field");
                }
                else if(address.getText().toString().equals("")){
                    address.setError("Please fill up field");
                }
                else {

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("LOGGED_IN", true);

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
            }
        });
    }
}
