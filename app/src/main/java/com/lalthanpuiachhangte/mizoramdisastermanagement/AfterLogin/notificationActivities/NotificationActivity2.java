package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.notificationActivities;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Relief;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;
import com.lalthanpuiachhangte.mizoramdisastermanagement.tools.NotificationAdapter2;

import java.util.ArrayList;

public class NotificationActivity2 extends AppCompatActivity {
    String TAG = "TAG";
    ArrayList<Relief> allRelief;
    RecyclerView recyclerView;
    NotificationAdapter2 notificationAdapter2;

    public String ROLE;
    public String TOPIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification2);

        ROLE = getIntent().getStringExtra("ROLE");
        TOPIC = getIntent().getStringExtra("TOPIC");

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


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

                        notificationAdapter2 = new NotificationAdapter2();
                        notificationAdapter2 = new NotificationAdapter2(allRelief, ROLE);

                        recyclerView.setAdapter(notificationAdapter2);

                        Log.i("TAG","serr"+reliefResult.get(0).getserialNumber());

                        Log.i("TAG","serr"+reliefResult);

                        //   go();
                        // intent.putParcelableArrayListExtra("reliefResult", reliefResult);

                        //startActivity(intent);

                    }
                });








//        Bundle extra = getIntent().getExtras();
//        String ROLE = getIntent().getStringExtra("ROLE");
//
//        if(extra!= null){
//            allRelief = extra.getParcelableArrayList("reliefResult");
//        }




    }

    public void Noti1(View view) {
        Intent intent = new Intent (this,NotificationActivity1.class);
        intent.putExtra("ROLE", ROLE);
        intent.putExtra("TOPIC", TOPIC );
        finish();
        startActivity(intent);
    }
}
