package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.notificationActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.GlobalNotification;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;
import com.lalthanpuiachhangte.mizoramdisastermanagement.tools.NotificationAdapter;
import com.lalthanpuiachhangte.mizoramdisastermanagement.tools.NotificationGlobalAdapter;

import java.util.ArrayList;

public class GlobalNotificationReceiverActivity extends AppCompatActivity {

    NotificationGlobalAdapter notificationGlobalAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_notification_receiver);

        recyclerView = findViewById(R.id.recyclerViewGlobal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        String  url = MainActivity.ipAddress+ "/getGlobalNotification" ;

        Ion.with(this)
                .load(url)
                .as(new TypeToken<ArrayList<GlobalNotification>>(){})
                .setCallback(new FutureCallback<ArrayList<GlobalNotification>>() {
                    @Override
                    public void onCompleted(Exception e, ArrayList<GlobalNotification> globalResult) {
                        // NotificationAdapter.addNotify(result);
                        if (globalResult == null) {
                            Toast.makeText(getApplicationContext(), "No report yet!.locality may not be maaping in the db", Toast.LENGTH_SHORT).show();
                        } else {

                            notificationGlobalAdapter = new NotificationGlobalAdapter();
                            notificationGlobalAdapter = new NotificationGlobalAdapter(globalResult);
                            recyclerView.setAdapter(notificationGlobalAdapter);

                            Log.d("TAG", "Result: " + globalResult.get(0).getSerialNumber());
                        }
                    }
                });
    }
}
