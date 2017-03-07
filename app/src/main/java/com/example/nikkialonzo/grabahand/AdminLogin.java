package com.example.nikkialonzo.grabahand;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gerard on 2/28/2017.
 */

public class AdminLogin extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    GrabEndpoint apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        apiService = new RestClient().getApiService();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                LoginInfo loginInfo = new LoginInfo(email.getText().toString(),password.getText().toString());
                Call<LoginResult> call = apiService.loginUser(loginInfo);
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        LoginResult loginResult = response.body();
                        try {
                            if (loginResult.getSuccess() == 1) {
                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AdminLogin.this);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("ADMIN_NAME", loginResult.getAdmin().getName());
                                editor.putString("ADMIN_EMAIL", loginResult.getAdmin().getEmail());
                                editor.putInt("ADMIN_PHONE", loginResult.getAdmin().getPhone());
                                editor.putString("INSTITUTION_NAME", loginResult.getAdmin().getInstitutionName());
                                editor.putInt("JOB_ID", loginResult.getAdmin().getJobId());
                                editor.apply();

                                Intent intent = new Intent(AdminLogin.this, MapActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(AdminLogin.this, loginResult.getMsg() ,Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(AdminLogin.this, "Login Failed.",Toast.LENGTH_SHORT).show();
                    }
                });*/
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("LOGGED_IN_ADMIN", true);
                editor.apply();
                editor.commit();

                Intent intent = new Intent(AdminLogin.this, MapActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminLogin.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
