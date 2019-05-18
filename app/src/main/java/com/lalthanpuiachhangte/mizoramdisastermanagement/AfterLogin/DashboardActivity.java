package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.User;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

public class DashboardActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefEditor;
    Button rescueMeButton;

    static User mUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        rescueMeButton = findViewById(R.id.rescueMeButton);

        //GET THE SHARE PREFERENCE OF THE USERDETAILS
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userObject","");
        mUser = gson.fromJson(json, User.class);

        //runtime permission for phone call
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }


        rescueMeButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //Toast.makeText(getApplicationContext(),"Long Click",Toast.LENGTH_SHORT).show();

                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"777"));
                startActivity(callIntent);
                return false;
            }
        });
    }

    public void requestReliefClick(View view) {
        Intent intent = new Intent(this, ReliefFormActivity.class);
        //xintent.putExtra("mUser", mUser);
        startActivity(intent);

    }

    public void incidentReportClick(View view) {
        Intent intent = new Intent(this, ReportIncidentFormActivity.class);
        //xintent.putExtra("mUser", mUser);
        startActivity(intent);
    }

    public void signoutClick(View view) {

        prefEditor = sharedPreferences.edit();
        //CLEAR EVERYTHING
        prefEditor.putString("userObject","");
        prefEditor.putString("username","");
        prefEditor.putString("password","");
        prefEditor.commit();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void rescueClick(View view) {
        Toast.makeText(this,"Long press to make a call",Toast.LENGTH_SHORT).show();

    }
}