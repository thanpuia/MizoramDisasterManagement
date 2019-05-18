package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.User;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

public class DashboardActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefEditor;

    static User mUser = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        //GET THE SHARE PREFERENCE OF THE USERDETAILS
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userObject","");
        mUser = gson.fromJson(json, User.class);
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
}