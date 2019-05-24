package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Officer;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Relief;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.User;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.DashboardActivity.mUser;

public class ReliefFormActivity extends AppCompatActivity {

    AutoCompleteTextView materialET;
    Spinner districtSpinner;
    //Spinner localitySpinner;
    AutoCompleteTextView locality;

    EditText detailsET;
    EditText landmarksET;
    EditText quantityET;
    EditText disasterDetailsET;

    Button reliefButton;

    Officer mOfficer = new Officer();
    Relief mReleif = new Relief();

    public String tempDistrict;
    public String tempLocality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relief_form);

        Intent intent = getIntent();
     //  User mUser = (User) intent.getSerializableExtra("mUser");

     //   detailsET = findViewById(R.id.details);
        landmarksET = findViewById(R.id.landmarks);
        materialET = findViewById(R.id.material);
        quantityET = findViewById(R.id.quantity);

        disasterDetailsET = findViewById(R.id.disasterDetails);
        districtSpinner = findViewById(R.id.district);
       // localitySpinner = findViewById(R.id.locality);
        locality = findViewById(R.id.locality);

        reliefButton = findViewById(R.id.requestReliefButton);




        //LOCALITY EDIT TEXT
  /*      String[] ProgLanguages = { "Java", "C", "C++", ".Net", "PHP", "Perl",
                "Objective-c", "Small-Talk", "C#", "Ruby", "ASP", "ASP .NET" };
        autoTextView = (AutoCompleteTextView) findViewById(R.id.autocompleteEditTextView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, ProgLanguages);
        //Used to specify minimum number of
        //characters the user has to type in order to display the drop down hint.
        autoTextView.setThreshold(1);
        //Setting adapter
        autoTextView.setAdapter(arrayAdapter);
*/

        //ArrayAdapter<String> localityAdapter = new ArrayAdapter<>(this,android.R.layout.select_dialog_item,R.array.locality);
        ArrayAdapter<CharSequence> localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.locality,android.R.layout.select_dialog_item);
        locality.setThreshold(1);
        locality.setAdapter(localityAdapter);



        //AUTO COMPLETE TEXTVIEW
        //1. Material Request
        String[] countries = getResources().getStringArray(R.array.material_lists);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries);
        materialET.setAdapter(adapter);

        //2. District
        ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(this,R.array.district,android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(districtAdapter);

        //CHANGE THE LOCALITY DYNAMICALLY WITH DISTRICT
        //3. Locality /Village

//
//        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                tempDistrict = districtSpinner.getSelectedItem().toString();
//                ArrayAdapter<CharSequence> localityAdapter = null;
//
//                if(tempDistrict.equals("Mamit")) {
//                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Mamit, android.R.layout.simple_spinner_dropdown_item);
//                }else if (tempDistrict.equals("Kolasib")){
//                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Kolasib,android.R.layout.simple_spinner_dropdown_item);
//                } else if (tempDistrict.equals("Aizawl")){
//                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Aizawl,android.R.layout.simple_spinner_dropdown_item);
//                } else if (tempDistrict.equals("Champhai")){
//                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Champhai,android.R.layout.simple_spinner_dropdown_item);
//                }else if (tempDistrict.equals("Serchhip")){
//                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Serchhip,android.R.layout.simple_spinner_dropdown_item);
//                }else if (tempDistrict.equals("Lunglei")){
//                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Lunglei,android.R.layout.simple_spinner_dropdown_item);
//                } else if (tempDistrict.equals("Lawngtlai")){
//                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Lawngtlai,android.R.layout.simple_spinner_dropdown_item);
//                } else if (tempDistrict.equals("Siaha")){
//                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Siaha,android.R.layout.simple_spinner_dropdown_item);
//                }
//
//                localitySpinner.setAdapter(localityAdapter);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


       // districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }

    public void requestReliefClick(View view) {
        //reliefButton.setEnabled(false);
        reliefButton.setVisibility(View.GONE);
        //1.GET THE DATA
        //1.1 Get From The User Populate
        String tempLandmarks =  String.valueOf(landmarksET.getText());
        String tempMaterial =       String.valueOf(materialET.getText());
        String tempQuantity =       String.valueOf(quantityET.getText());
        String tempDetails = String.valueOf(disasterDetailsET.getText());
        tempLocality = locality.getText().toString();

//        String tempDistrict = "";//districtET.getSelectedItem().toString();
//        String tempLocality = "";//localityET.getSelectedItem().toString();

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
/*
        //ONLY BOTH ARE FILLED
      //  if(!tempDistrict.equals("")){
            String url = MainActivity.ipAddress+ "/test/" + tempLocality;
            Ion.with(this)
                    .load(url)
                    .as(new TypeToken<Officer>(){})
                    .setCallback(new FutureCallback<Officer>() {
                        @Override
                        public void onCompleted(Exception e, Officer result) {
                            if(result==null){
                                Toast.makeText(getApplicationContext(),"locality may not be maaping in the db",Toast.LENGTH_SHORT).show();
                               // reliefButton.setEnabled(true);

                             //   reliefButton.setVisibility(View.VISIBLE);

                            }
                            else{
                                 String tempZonalOfficerName = result.getOfficerName();
                                 String tempZonalOfficerContact = result.getOfficerContact();

                                 //2.1() SET THE ZONAL OFFICER DETAILS HERE
                                mReleif.setOfficerContact(tempZonalOfficerContact);
                               // mReleif.setZonalOfficerId();
                                mReleif.setOfficerName(tempZonalOfficerName);
                               // mReleif.setZoneId();
                           }
                        }
                    });*/
      //  }else{
           // reliefButton.setEnabled(true);
        //    reliefButton.setVisibility(View.VISIBLE);
      //  }

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


        String url2 = MainActivity.ipAddress + "/post/relief/";

        //UPLOADING THE RELIEF REQUEST FORM
        Ion.with(this)
                .load(url2)
                .setJsonPojoBody(mReleif)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.i("JSON Response","Result: "+result);

                        reliefButton.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(),"Successfully sent", Toast.LENGTH_SHORT).show();

                        //clear the fields
                        materialET.setText("");
                        quantityET.setText("");
                        landmarksET.setText("");
                        disasterDetailsET.setText("");
                    }
                });

    }
}
