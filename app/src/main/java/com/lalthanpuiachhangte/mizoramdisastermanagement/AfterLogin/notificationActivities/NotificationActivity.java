package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.notificationActivities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Relief;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    String TAG = "TAG";
    ArrayList<Incident> allIncident;
    ArrayList<Relief> allRelief;
    public String ROLE;
    public String TOPIC;

    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ROLE = getIntent().getStringExtra("ROLE");
        TOPIC = getIntent().getStringExtra("TOPIC");

        if(ROLE.equals("OFFICER")) {
            url = MainActivity.ipAddress+ "/notifyOfficer/incident/"+TOPIC ;
            ROLE = "OFFICER";
        } else{
            url = MainActivity.ipAddress+ "/notifyCitizen/incident/"+TOPIC ;
            ROLE = "CITIZEN";
        }

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


                            Log.d(TAG, "Result: " + incidentResult.get(0).getUsername());
                        }






                    }
                });

        //GET ALL THE RELIEF REPORT
        String url2;
        if(ROLE.equals("OFFICER")){
            url2 =  MainActivity.ipAddress+ "/notifyOfficer/relief/"+TOPIC ;
        }else
            url2 =  MainActivity.ipAddress+ "/notifyCitizen/relief/"+TOPIC ;

        Ion.with(getApplicationContext())
                .load(url2)
                .as(new TypeToken<ArrayList<Relief>>(){})
                .setCallback(new FutureCallback<ArrayList<Relief>>() {
                    @Override
                    public void onCompleted(Exception e, ArrayList<Relief> reliefResult) {

                        allRelief = reliefResult;
                        Log.d(TAG, "Result: " + reliefResult.get(0).getUsername());

                        go();
                        // intent.putParcelableArrayListExtra("reliefResult", reliefResult);

                        //startActivity(intent);

                    }
                });









        ////////////////

//        Bundle extra = getIntent().getExtras();
//        ROLE = getIntent().getStringExtra("ROLE");
//
//        if(extra!= null){
//            allIncident = extra.getParcelableArrayList("incidentResult");
//            allRelief = extra.getParcelableArrayList("reliefResult");
//        }
//
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//
//        notificationAdapter = new NotificationAdapter();
//        notificationAdapter = new NotificationAdapter(allIncident,ROLE);
//
//        recyclerView.setAdapter(notificationAdapter);

    }

    public void go(){

        Intent intent = new Intent(this, NotificationActivity1.class);

        intent.putParcelableArrayListExtra("incidentResult", allIncident);
        intent.putExtra("ROLE", ROLE);
        finish();
        startActivity(intent);
    }

//    public void nextClick(View view) {
//
//        Intent intent = new Intent (this,NotificationActivity2.class);
//
//        intent.putParcelableArrayListExtra("reliefResult", allRelief);
//        intent.putExtra("ROLE", ROLE);
//
//        startActivity(intent);
//
//    }
}
