package com.lalthanpuiachhangte.mizoramdisastermanagement.notification;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.DashboardActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;
import com.lalthanpuiachhangte.mizoramdisastermanagement.tools.DatabaseHelper;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class MyFCMClass extends FirebaseMessagingService {

    private final String TAG = "TAG";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefEditor;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        DatabaseHelper db = new DatabaseHelper(MyFCMClass.this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.d(TAG, "inside MyFCMCLASS OnMsgReceived");
        if (remoteMessage.getNotification() != null) {

            Log.d(TAG, "Title: " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Body: " + remoteMessage.getNotification().getBody());

        //   string= remoteMessage.getNotification().getBody();
          //  MainActivity.change(string);
          //  messageReceived = true;
          //  Toast.makeText(getApplicationContext(),"helo",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "OnMsgReceived msg not null");
        }

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Data: " + remoteMessage.getData());

            //CONVERTING TO JSON
            Map<String, String> params = remoteMessage.getData();
            
            JSONObject object = new JSONObject(params);

            //JSON to OBJECT
            Gson gson = new Gson();
            String jsonInString = String.valueOf(object);
          //  String jsonInString = "{\"userId\":\"1\",\"userName\":\"Yasir\"}";
            Incident userIncident= gson.fromJson(jsonInString, Incident.class);

          //  db.onUpgrade(,1,1);
            db.insertNotification(userIncident);
          //  db.insertNotification(new Incident());

            Log.e(TAG, "object test: " + userIncident.getUsername());
            Log.d(TAG, "data is here, hurray!!");

        }else
            Log.d(TAG, "nay data");

        Log.d(TAG, "end of msg received");

        //RETRIEVE THE DATABASE
        Log.d(TAG,"Reading all notifications");
        List<Incident> allIncident = db.getAllNotification();

        for(Incident icdt :allIncident) {
            String log ="Serial: "+icdt.getSerialNumber() +" ,Name: "+icdt.getUsername()+" ,Veng: " + icdt.getLocality();
            Log.d(TAG,log);
        }

        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("body","");
        startActivity(intent);

    }
}
