package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.User;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;
import com.lalthanpuiachhangte.mizoramdisastermanagement.location.GetLocation;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import android.Manifest;
public class DashboardActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefEditor;
    Button rescueMeButton;

    static User mUser = new User();

    public String TOPIC = "";//THIS WILLL BE TAKEN FROM THE SHARED PREFERENCE OF THE APP. IT SHOULD BE UNIQUE TO EVERY APP
    private final String TAG = "JSA-FCM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        Log.d(TAG, "oncreate Starting");

        rescueMeButton = findViewById(R.id.rescueMeButton);
        checkLocationPermission();

        //GET THE SHARE PREFERENCE OF THE USERDETAILS
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userObject","");
        mUser = gson.fromJson(json, User.class);

        //CREATING INSTANCE FOR FCM
        TOPIC = "7810911046";//mUser.getPhoneNo(); //GETTING THE TOPIC DYNAMICALLY SO THAT IT CAN RECEIVED THE PRESCRIBE NOTIFICATION
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("TAG", msg);
                        Toast.makeText(DashboardActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "inside onCreate > onComplete");
                    }
                });
        Log.d(TAG, "inside onCreate");


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
        Log.d(TAG, "onCreate Ending");
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
        prefEditor.putString("phoneNo","");
        prefEditor.putString("password","");
        prefEditor.commit();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void rescueClick(View view) {
        Toast.makeText(this,"Long press to make a call",Toast.LENGTH_SHORT).show();

    }

    public void mapClick(View view) {


    }



    //LOCATION PERMISSION AT RUNTIME
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("PERMISSION")
                        .setMessage("DO YOU ALLOW")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(DashboardActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                       // locationManager.requestLocationUpdates(provider, 400, 1, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

    public static Location getLocation(final Context context){

        final Location[] mLocation = {null};// = new Location();

        //Toast.makeText(DashboardActivity.class,"GOS: "+ SmartLocation.with(getApplicationContext()).location().state().isGpsAvailable()
              //  ,Toast.LENGTH_SHORT).show();
        SmartLocation.with(context).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        //Toast.makeText(context,"Location"+location.getLatitude(),Toast.LENGTH_SHORT).show();
                        mLocation[0] = location;
                        //Toast.makeText(context,"Location in array"+mLocation[0].getLatitude(),Toast.LENGTH_SHORT).show();
                    }
                });
        return mLocation[0];
    }

    public void zonalOfficerClick(View view) {
        Intent intent = new Intent(getApplicationContext(),ZonalOfficerActivity.class);
        startActivity(intent);
    }
}