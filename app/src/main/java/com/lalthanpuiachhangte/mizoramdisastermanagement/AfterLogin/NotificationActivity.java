package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;
import com.lalthanpuiachhangte.mizoramdisastermanagement.tools.DatabaseHelper;
import com.lalthanpuiachhangte.mizoramdisastermanagement.tools.NotificationAdapter;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    ArrayList<Incident> allIncident;
    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    String TAG ="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        DatabaseHelper db = new DatabaseHelper(NotificationActivity.this);

        allIncident = (ArrayList<Incident>) db.getAllNotification();

        notificationAdapter = new NotificationAdapter();

        notificationAdapter = new NotificationAdapter(allIncident);
        recyclerView.setAdapter(notificationAdapter);
    }
}
