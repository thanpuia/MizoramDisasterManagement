package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.DashboardActivity.mUser;

public class ReportIncidentFormActivity extends AppCompatActivity {

    Spinner disasterTypeSpinner;
    EditText landmarkET;
    EditText disasterDetailsET;

    Spinner district;
    Spinner locality;

    Button incidentButton;

    Officer mOfficer ;
    Incident mIncident;
    public String tempDistrict;
    public String tempLocality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident_form);

        mOfficer = new Officer();
        mIncident = new Incident();

        disasterTypeSpinner = findViewById(R.id.disasterType);
        landmarkET = findViewById(R.id.landmarks);
        disasterDetailsET = findViewById(R.id.disasterDetails);

        district = findViewById(R.id.district);
        locality = findViewById(R.id.locality);

        incidentButton = findViewById(R.id.incidentReportButton);

        //1. Spinner auto fill for Disaster type
        ArrayAdapter<CharSequence> disasterTypeAdapter = ArrayAdapter.createFromResource(this,R.array.disasterType,android.R.layout.simple_spinner_dropdown_item);
        disasterTypeSpinner.setAdapter(disasterTypeAdapter);


        //2. Spinner auto fill for district
        ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(this,R.array.district,android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(districtAdapter);

        //Dynamically change the locality according to the district
        //CHANGE THE LOCALITY DYNAMICALLY WITH DISTRICT
        //3. Locality /Village

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tempDistrict = district.getSelectedItem().toString();
                ArrayAdapter<CharSequence> localityAdapter = null;

                if(tempDistrict.equals("Mamit")) {
                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Mamit, android.R.layout.simple_spinner_dropdown_item);
                }else if (tempDistrict.equals("Kolasib")){
                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Kolasib,android.R.layout.simple_spinner_dropdown_item);
                } else if (tempDistrict.equals("Aizawl")){
                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Aizawl,android.R.layout.simple_spinner_dropdown_item);
                } else if (tempDistrict.equals("Champhai")){
                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Champhai,android.R.layout.simple_spinner_dropdown_item);
                }else if (tempDistrict.equals("Serchhip")){
                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Serchhip,android.R.layout.simple_spinner_dropdown_item);
                }else if (tempDistrict.equals("Lunglei")){
                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Lunglei,android.R.layout.simple_spinner_dropdown_item);
                } else if (tempDistrict.equals("Lawngtlai")){
                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Lawngtlai,android.R.layout.simple_spinner_dropdown_item);
                } else if (tempDistrict.equals("Siaha")){
                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Siaha,android.R.layout.simple_spinner_dropdown_item);
                }

                locality.setAdapter(localityAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    public void incidentReportClick(View view) {

        //make the button disable to avoid sendng twince
        //incidentButton.setEnabled(false);
        incidentButton.setVisibility(View.GONE);

        //1. GET THE DATA
        //1.1 GET THE DATA FROM THE USER POPULATE
        String tempDisasterType = String.valueOf(disasterTypeSpinner.getSelectedItem());
        String tempLandmark = String.valueOf(landmarkET.getText());
        String tempDisasterDetails = String.valueOf(disasterDetailsET.getText());
        tempLocality = locality.getSelectedItem().toString();

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
            String url = MainActivity.ipAddress + "/test/" + tempLocality;
            Ion.with(this)
                    .load(url)
                    .as(new TypeToken<Officer>(){})
                    .setCallback(new FutureCallback<Officer>() {
                        @Override
                        public void onCompleted(Exception e, Officer result) {
                             if(result==null){
                                 Toast.makeText(getApplicationContext(),"locality may not be maaping in the db",Toast.LENGTH_SHORT).show();
                                // incidentButton.setEnabled(true);

                             }
                            else{
                            String tempZonalOfficerName = result.getOfficerName();
                            String tempZonalOfficerContact = result.getOfficerContact();

                            //2.1() SET THE ZONAL OFFICER DETAILS HERE
                            mIncident.setOfficerContact(tempZonalOfficerContact);
                            // mReleif.setZonalOfficerId();
                            mIncident.setOfficerName(tempZonalOfficerName);
                            // mReleif.setZoneId();
                                }
                        }


                    });
        }else{
            incidentButton.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"Try again",Toast.LENGTH_SHORT).show();

        }


        //2.
        // 2.1INSERT THE DATA

        mIncident.setDisasterType(tempDisasterType);
        mIncident.setLocality(tempLocality);
        mIncident.setLandmarks(tempLandmark);
        mIncident.setDisastersDetails(tempDisasterDetails);
        //mIncident.setDetails();
        mIncident.setDistrict(tempDistrict);
        //mIncident.setLng();
        //mIncident.setLat();
        mIncident.setLocation(tempLocality);
        mIncident.setUsername(sharedPreferenceUsername);
        mIncident.setPhone(sharedPreferencePhone);
        mIncident.setReportOn(mCurrentTime);
        //mIncident.setUserId();
        //mIncident.setZoneId();

        String url = MainActivity.ipAddress + "/post/incident";
        //2.2 UPLOADING THE RELIEF REQUEST FORM
        Ion.with(this)
                .load(url)
                .setJsonPojoBody(mIncident)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.i("JSON Response","Result: "+result);

                       // mIncident.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(),"Successfully sent", Toast.LENGTH_SHORT).show();
                        incidentButton.setVisibility(View.VISIBLE);

                        //clear the fields
                        landmarkET.setText("");
                        disasterDetailsET.setText("");

                    }
                });

        //reliefButton.setVisibility(View.VISIBLE);
    }
}
