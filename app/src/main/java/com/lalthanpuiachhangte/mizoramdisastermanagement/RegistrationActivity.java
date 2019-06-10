package com.lalthanpuiachhangte.mizoramdisastermanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrationActivity extends AppCompatActivity {

    EditText username;
    EditText phoneNumber;
    EditText email;
    EditText password;
    EditText confirmPassword;
    EditText alternateName;
    EditText alternateNumber;
    EditText emergencyContactNumber;
    EditText emergencyContactName;
    Spinner district;
    AutoCompleteTextView locality;
    CheckBox volunteer;
    Button registrationButton;


    public String tempDistrict;
    public String tempLocality;
    public User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.username);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        alternateName = findViewById(R.id.altContactName);
        alternateNumber = findViewById(R.id.altContactNo);
        emergencyContactNumber = findViewById(R.id.emergencyContactNumber);
        emergencyContactName = findViewById(R.id.emergencyContactName);
        district = findViewById(R.id.district);
        locality = findViewById(R.id.locality);
        volunteer = findViewById(R.id.volunteerWithUs);
        registrationButton = findViewById(R.id.registrationButton);



        //2. District
        ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(this,R.array.district,android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(districtAdapter);

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

                    localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.locality,android.R.layout.simple_spinner_dropdown_item);

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

    public void RegistrationClick(View view) {

        registrationButton.setVisibility(View.GONE);

        tempLocality = String.valueOf(locality.getText());

        //GET THE D AtA AND PUSH TO THE OBJECT
        mUser = new User();
        mUser.setUsername(username.getText().toString());
        mUser.setPhoneNo(phoneNumber.getText().toString());
        mUser.setEmail(email.getText().toString());
        mUser.setPassword(password.getText().toString());
        mUser.setAltContactName(alternateName.getText().toString());
        mUser.setAltContactNo(alternateNumber.getText().toString());
        mUser.setEmergencyContactName(emergencyContactName.getText().toString());
        mUser.setEmergencyContactNo(emergencyContactNumber.getText().toString());
        mUser.setDistrict(tempDistrict);
        mUser.setLocality(tempLocality);

        //SET THIS DEFAULT TO CITIZEN
        mUser.setUserRole("CITIZEN");


        if(volunteer.isChecked())
            mUser.setVolunteer("yes");
        else
            mUser.setVolunteer("no");

        //GET THE CURRENT TIME
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy");
        String mCurrentTime=dateFormat.format(date);
        System.out.println("Current time of the day using Calendar - 24 hour format: "+ mCurrentTime);

        mUser.setCreatedAt(mCurrentTime);

        //uploading
        uploading(mUser);
    }

    public void uploading(User mUser) {

        String url = MainActivity.ipAddress + "/signup/newUser";

        //UPLOADING THE RELIEF REQUEST FORM
        Ion.with(this)
                .load(url)
                .setJsonPojoBody(mUser)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.i("JSON Response","Result: "+result);

                        JsonElement subjectJE = result.get("subject");
                        JsonElement body1JE = result.get("body1");


                        String subject = subjectJE.getAsString();
                        String body1 = body1JE.getAsString();

                        registrationButton.setVisibility(View.VISIBLE);

                        if(body1.equals("duplicate")){
                            Toast.makeText(getApplicationContext(),"Duplicate phone number", Toast.LENGTH_SHORT).show();
                        }else if(body1.equals("saved")){
                            Toast.makeText(getApplicationContext(),"Successfully sent", Toast.LENGTH_SHORT).show();

                        }
//
//                        //clear the fields
//                        materialET.setText("");
//                        quantityET.setText("");
//                        landmarksET.setText("");
//                        disasterDetailsET.setText("");
                    }
                });
    }

}
