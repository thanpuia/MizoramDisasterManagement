package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.notificationActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Relief;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;
import com.lalthanpuiachhangte.mizoramdisastermanagement.tools.NotificationAdapter;

import java.util.ArrayList;

public class NotificationActivity1 extends AppCompatActivity {
    String TAG = "TAG";
    ArrayList<Incident> allIncident;
    public String ROLE;
    public String TOPIC;

    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;

    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification1);

        ROLE = getIntent().getStringExtra("ROLE");
        TOPIC = getIntent().getStringExtra("TOPIC");

        if(ROLE.equals("OFFICER")) {
            url = MainActivity.ipAddress+ "/notifyOfficer/incident/"+TOPIC ;
            ROLE = "OFFICER";
        } else{
            url = MainActivity.ipAddress+ "/notifyCitizen/incident/"+TOPIC ;
            ROLE = "CITIZEN";
        }
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //GET ALL THE INCIDENT REPORT
        Ion.with(this)
                .load(url)
                .as(new TypeToken<ArrayList<Incident>>(){})
                .setCallback(new FutureCallback<ArrayList<Incident>>() {
                    @Override
                    public void onCompleted(Exception e, ArrayList<Incident> incidentResult) {
                        // NotificationAdapter.addNotify(result);
                        if (incidentResult == null) {
                            Toast.makeText(getApplicationContext(), "No report yet!.locality may not be maaping in the db", Toast.LENGTH_SHORT).show();
                        } else {

                            allIncident = incidentResult;

                            notificationAdapter = new NotificationAdapter();
                            notificationAdapter = new NotificationAdapter(allIncident,ROLE);

                            recyclerView.setAdapter(notificationAdapter);

                            Log.d(TAG, "Result: " + incidentResult.get(0).getSerialNumber());
                        }
                    }
                });



      //  Bundle extra = getIntent().getExtras();
      //  ROLE = getIntent().getStringExtra("ROLE");

       // if(extra!= null){
       //     allIncident = extra.getParcelableArrayList("incidentResult");
           // allRelief = extra.getParcelableArrayList("reliefResult");
      //  }


    }

    public void Noti2(View view) {

        Intent intent = new Intent(this,NotificationActivity2.class);

        intent.putExtra("ROLE", ROLE);
        intent.putExtra("TOPIC", TOPIC );
        finish();
        startActivity(intent);
    }
}
