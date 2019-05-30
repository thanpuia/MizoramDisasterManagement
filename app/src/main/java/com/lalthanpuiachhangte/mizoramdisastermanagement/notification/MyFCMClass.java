package com.lalthanpuiachhangte.mizoramdisastermanagement.notification;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFCMClass extends FirebaseMessagingService {

 private final String TAG = "JSA-FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "inside MyFCMCLASS OnMsgReceived");
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Title: " + remoteMessage.getNotification().getTitle());
            Log.e(TAG, "Body: " + remoteMessage.getNotification().getBody());
         //   string= remoteMessage.getNotification().getBody();
            //  MainActivity.change(string);
          //  messageReceived = true;
            Log.d(TAG, "OnMsgReceived msg not null");
        }

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data: " + remoteMessage.getData());
        }
        Log.d(TAG, "end of msg received");
      //  Intent intent = new Intent(this,MainActivity.class);
      //  intent.putExtra("body",string);
      //  startActivity(intent);
    }
}
