package com.example.nikkialonzo.grabahand;

import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by JONAS on 2/27/2017.
 */

public class GrabFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Intent intent = new Intent();
        intent.setAction("com.my.app.onMessageReceived");
        sendBroadcast(intent);
    }
}
