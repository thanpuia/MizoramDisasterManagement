package com.lalthanpuiachhangte.mizoramdisastermanagement.notification;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.DashboardActivity;

import java.security.interfaces.DSAKey;

public class MyFCMClass extends FirebaseMessagingService {

 private final String TAG = "TAG";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "inside MyFCMCLASS OnMsgReceived");
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Title: " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Body: " + remoteMessage.getNotification().getBody());
         //   string= remoteMessage.getNotification().getBody();
            //  MainActivity.change(string);
          //  messageReceived = true;
        //    Toast.makeText(getApplicationContext(),"helo",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "OnMsgReceived msg not null");
        }

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Data: " + remoteMessage.getData());
      //      Log.e(TAG, "Data: " + remoteMessage.getData());
            Log.d(TAG, "data is here, hurray!!");

        }else
            Log.d(TAG, "nay data");

        Log.d(TAG, "end of msg received");
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("body","df");
        startActivity(intent);

    }


}
