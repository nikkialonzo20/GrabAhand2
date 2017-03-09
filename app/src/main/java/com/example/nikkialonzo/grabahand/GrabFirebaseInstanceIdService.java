package com.example.nikkialonzo.grabahand;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by JONAS on 2/27/2017.
 */

public class GrabFirebaseInstanceIdService extends FirebaseInstanceIdService{

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(GrabFirebaseInstanceIdService.this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("TOKEN", recent_token);
        editor.apply();
        Log.d("on refresh: ", recent_token);
    }
}
