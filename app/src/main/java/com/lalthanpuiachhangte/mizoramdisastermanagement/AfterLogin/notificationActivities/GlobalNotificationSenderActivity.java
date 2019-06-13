package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.notificationActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.GlobalNotification;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GlobalNotificationSenderActivity extends AppCompatActivity {

    EditText subjectET, bodyET, extraET;
    String username, designation, subject, body, time, extra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_notification_sender);

        username = getIntent().getStringExtra("username");
        designation = getIntent().getStringExtra("designation");

        subjectET = findViewById(R.id.subjectEditText);
        bodyET = findViewById(R.id.bodyEditText);
        extraET = findViewById(R.id.extraEditText);




    }

    public void sentGlobalNotification(View view) {

        //1.3 Get The Current Time
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy");
        String mCurrentTime=dateFormat.format(date);
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ mCurrentTime);

        subject = subjectET.getText().toString();
        body = bodyET.getText().toString();
        try{
            extra = extraET.getText().toString();
        }catch (Exception e){
            extra = "";
        }

        time = mCurrentTime;

        GlobalNotification sender = new GlobalNotification();

        sender.setName(username);
        sender.setDesignation(designation);
        sender.setSubject(subject);
        sender.setBody(body);
        sender.setReportOn(time);
        sender.setExtra(extra);
        String url2 = MainActivity.ipAddress + "/setGlobalNotification";

        Ion.with(this)
                .load(url2)
                .setJsonPojoBody(sender)
                .as(new TypeToken<ArrayList<GlobalNotification>>(){})
                .setCallback(new FutureCallback<ArrayList<GlobalNotification>>() {
                    @Override
                    public void onCompleted(Exception e, ArrayList<GlobalNotification> result) {
                        Log.i("ATG",result+"");

                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();

                        subjectET.setText("");
                        bodyET .setText("");
                        extraET.setText("");
                    }
                });
    }
}
