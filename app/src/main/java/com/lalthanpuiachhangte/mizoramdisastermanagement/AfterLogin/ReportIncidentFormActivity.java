package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Incident;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Officer;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Relief;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.DashboardActivity.mUser;

public class ReportIncidentFormActivity extends AppCompatActivity {

    EditText disasterTypeET;
    EditText landmarkET;
    EditText disasterDetailsET;
    EditText statusET;

    Spinner district;
    Spinner locality;

    Button incidentButton;

    Officer mOfficer ;
    Incident mIncident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident_form);

        mOfficer = new Officer();
        mIncident = new Incident();

        disasterTypeET = findViewById(R.id.disasterType);
        landmarkET = findViewById(R.id.landmarks);
        disasterDetailsET = findViewById(R.id.disasterDetails);
        statusET = findViewById(R.id.status);

        district = findViewById(R.id.district);
        locality = findViewById(R.id.locality);

        incidentButton = findViewById(R.id.incidentReportButton);

        ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(this,R.array.district,android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> mamitAdapter = ArrayAdapter.createFromResource(this,R.array.Mamit,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> kolasibAdapter = ArrayAdapter.createFromResource(this,R.array.Kolasib,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> aizawlAdapter = ArrayAdapter.createFromResource(this,R.array.Aizawl,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> champhaiAdapter = ArrayAdapter.createFromResource(this,R.array.Champhai,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> lungleiAdapter = ArrayAdapter.createFromResource(this,R.array.Lunglei,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> lawngtlaiAdapter = ArrayAdapter.createFromResource(this,R.array.Lawngtlai,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> siahaAdapter = ArrayAdapter.createFromResource(this,R.array.Siaha,android.R.layout.simple_spinner_dropdown_item);

        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        district.setAdapter(districtAdapter);

        locality.setAdapter(mamitAdapter);

    }

    public void incidentReportClick(View view) {

        //1. GET THE DATA
        //1.1 GET THE DATA FROM THE USER POPULATE
        String tempDisasterType = String.valueOf(disasterTypeET.getText());
        String tempLandmark = String.valueOf(landmarkET.getText());
        String tempDisasterDetails = String.valueOf(disasterDetailsET.getText());
        String tempStatus = String.valueOf(statusET.getText());
        String tempDistrict = district.getSelectedItem().toString();;
        String tempLocality = locality.getSelectedItem().toString();;

        //1.2 GET THE DATA FROM THE SHARED PREFERENCE
        String sharedPreferenceUsername = mUser.getUsername();
        String sharedPreferencePhone = mUser.getPhoneNo();

        //1.3 GET THE CURRENT TIME
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy");
        String mCurrentTime=dateFormat.format(date);
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ mCurrentTime);

        //1.4 GET THE ZONAL OFFICER DATA
        //ONLY BOTH ARE FILLED
        if(!tempDistrict.equals("") && !tempLocality.equals("")){
            String url = "http://10.180.243.3:8080/test/" + tempLocality;
            Ion.with(this)
                    .load(url)
                    .as(new TypeToken<Officer>(){})
                    .setCallback(new FutureCallback<Officer>() {
                        @Override
                        public void onCompleted(Exception e, Officer result) {
                            // if(result==null){
                            //     Toast.makeText(getApplicationContext(),"locality may not be maaping in the db",Toast.LENGTH_SHORT).show();

                            // }
                            //else{
                            String tempZonalOfficerName = result.getOfficerName();
                            String tempZonalOfficerContact = result.getOfficerContact();

                            //2.1() SET THE ZONAL OFFICER DETAILS HERE
                            mIncident.setOfficerContact(tempZonalOfficerContact);
                            // mReleif.setZonalOfficerId();
                            mIncident.setOfficerName(tempZonalOfficerName);
                            // mReleif.setZoneId();
                            //    }
                        }


                    });
        }else{
        }


        //2.
        // 2.1INSERT THE DATA

        mIncident.setDisasterType(tempDisasterType);
        mIncident.setLocality(tempLocality);
        mIncident.setLandmarks(tempLandmark);
        mIncident.setDisasterDetails(tempDisasterDetails);
        //mIncident.setDetails();
        mIncident.setDistrict(tempDistrict);
        //mIncident.setLng();
        //mIncident.setLat();
        mIncident.setLocation(tempLocality);
        mIncident.setUsername(sharedPreferenceUsername);
        mIncident.setPhone(sharedPreferencePhone);
        mIncident.setReportOn(mCurrentTime);
        mIncident.setStatus(tempStatus);
        //mIncident.setUserId();
        //mIncident.setZoneId();

        //2.2 UPLOADING THE RELIEF REQUEST FORM
        Ion.with(this)
                .load("http://10.180.243.3:8080/post/incident")
                .setJsonPojoBody(mIncident)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.i("JSON Response","Result: "+result);

                       // mIncident.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(),"Successfully sent", Toast.LENGTH_SHORT).show();
                    }
                });

        //reliefButton.setVisibility(View.VISIBLE);
    }
}
