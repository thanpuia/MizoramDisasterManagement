package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Officer;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Relief;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.User;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.DashboardActivity.mUser;

public class ReliefFormActivity extends AppCompatActivity {

    EditText detailsET;
    EditText landmarksET;
    EditText materialET;
    EditText quantityET;
    Spinner districtSpinner;
    Spinner localitySpinner;
    Button reliefButton;

    Officer mOfficer = new Officer();
    Relief mReleif = new Relief();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relief_form);

        Intent intent = getIntent();
     //  User mUser = (User) intent.getSerializableExtra("mUser");

        detailsET = findViewById(R.id.details);
        landmarksET = findViewById(R.id.landmarks);
        materialET = findViewById(R.id.material);
        quantityET = findViewById(R.id.quantity);

        districtSpinner = findViewById(R.id.district);
        localitySpinner = findViewById(R.id.locality);

        reliefButton = findViewById(R.id.requestReliefButton);

        ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(this,R.array.district,android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> mamitAdapter = ArrayAdapter.createFromResource(this,R.array.Mamit,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> kolasibAdapter = ArrayAdapter.createFromResource(this,R.array.Kolasib,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> aizawlAdapter = ArrayAdapter.createFromResource(this,R.array.Aizawl,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> champhaiAdapter = ArrayAdapter.createFromResource(this,R.array.Champhai,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> lungleiAdapter = ArrayAdapter.createFromResource(this,R.array.Lunglei,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> lawngtlaiAdapter = ArrayAdapter.createFromResource(this,R.array.Lawngtlai,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> siahaAdapter = ArrayAdapter.createFromResource(this,R.array.Siaha,android.R.layout.simple_spinner_dropdown_item);

        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        districtSpinner.setAdapter(districtAdapter);

        localitySpinner.setAdapter(mamitAdapter);

    }

    public void requestReliefClick(View view) {
        reliefButton.setVisibility(View.GONE);
        //1.GET THE DATA
        //1.1 Get From The User Populate
        String tempDetails =    String.valueOf(detailsET.getText());
        String tempLandmarks =  String.valueOf(landmarksET.getText());
        String tempMaterial =       String.valueOf(materialET.getText());
        String tempQuantity =       String.valueOf(quantityET.getText());

        String tempDistrict = districtSpinner.getSelectedItem().toString();
        String tempLocality = localitySpinner.getSelectedItem().toString();

        //1.2 Get From the Shared Preferences
        String sharedPreferenceUsername = mUser.getUsername();
        String sharedPreferencePhone = mUser.getPhoneNo();

        //1.3 Get The Current Time
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy");
        String mCurrentTime=dateFormat.format(date);
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ mCurrentTime);

        //.1.4 Get The Zonal Officer Data

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
                                mReleif.setOfficerContact(tempZonalOfficerContact);
                               // mReleif.setZonalOfficerId();
                                mReleif.setOfficerName(tempZonalOfficerName);
                               // mReleif.setZoneId();
                        //    }
                        }
                    });
        }else{
            reliefButton.setVisibility(View.VISIBLE);
        }

        //CREATE A NEW OBJECT WITH RELIEF ENTITY

        //2. SET ALL THE DATE INTO THE OBJECT
        mReleif.setDetails(tempDetails);
        mReleif.setDistrict(tempDistrict);
        mReleif.setLandmarks(tempLandmarks);
     //   mReleif.setLat();
     //   mReleif.setLng();
        mReleif.setLocality(tempLocality);
        mReleif.setMaterial(tempMaterial);
     //   mReleif.setMaterialId();
        mReleif.setUsername(sharedPreferenceUsername);
        mReleif.setPhone(sharedPreferencePhone);
        mReleif.setQuantity(tempQuantity);
        mReleif.setRequestOn(mCurrentTime);
      //  mReleif.setStatus();
      //  mReleif.setUserId();
     //the zonal officer details are populate above



        //UPLOADING THE RELIEF REQUEST FORM
        Ion.with(this)
                .load("http://10.180.243.3:8080/post/relief")
                .setJsonPojoBody(mReleif)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.i("JSON Response","Result: "+result);

                        reliefButton.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(),"Successfully sent", Toast.LENGTH_SHORT).show();
                    }
                });

        reliefButton.setVisibility(View.VISIBLE);
    }
}
