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

    // Declare properties
    EditText email;
    EditText password;
    Button login;
    GrabEndpoint apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Bind properties to their view
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        apiService = new RestClient().getApiService();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final String token = sharedPreferences.getString("TOKEN", "false");
        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();

        // Login button on click
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             LoginInfo loginInfo = new LoginInfo(email.getText().toString(),password.getText().toString(), token);
                Call<LoginResult> call = apiService.loginUser(loginInfo);
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        LoginResult loginResult = response.body();
                        try {
                            if (loginResult.getSuccess() == 1) {

                                editor.putBoolean("LOGGED_IN_ADMIN", true);
                                editor.putString("ADMIN_NAME", loginResult.getAdmin().getName());
                                editor.putString("ADMIN_EMAIL", loginResult.getAdmin().getEmail());
                                editor.putInt("ADMIN_PHONE", loginResult.getAdmin().getPhone());
                                editor.putString("INSTITUTION_NAME", loginResult.getAdmin().getInstitutionName());
                                editor.putInt("JOB_ID", loginResult.getAdmin().getJobId());
                                editor.apply();

                                Toast.makeText(AdminLogin.this, "Success",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdminLogin.this, MapActivity.class));
                                finish();
                            }else{
                                Toast.makeText(AdminLogin.this, loginResult.getMsg() ,Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(AdminLogin.this, "Login Failed.",Toast.LENGTH_SHORT).show();
                    }
                });
             }
        });
    }

    // Handling when the user presses the back button
    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdminLogin.this, MainActivity.class));
        finish();
    }
}
