package com.lalthanpuiachhangte.mizoramdisastermanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

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
    Spinner locality;
    CheckBox volunteer;


    public String tempDistrict;
    public String tempLocality;

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

                //get the datas and push to the object
                String tempUsername = username.getText().toString();
                String tempPhonenumber = phoneNumber.getText().toString();
                String tempEmail = phoneNumber.getText().toString();
                String tempPassword = password.getText().toString();
                String tempConfirmPassword = confirmPassword.getText().toString();
                String tempAlternateContactName = alternateName.getText().toString();
                String tempAlternateContactNumber = alternateNumber.getText().toString();
                String tempEmergencyContactName = emergencyContactName.getText().toString();
                String tempEmergencyContactNumber = emergencyContactNumber.getText().toString();

                Boolean volunteerBool = volunteer.isChecked();


                //uploading
                uploading();
            }
        });

    }

    public void uploading() {

        String url = MainActivity.ipAddress + "/post/relief";

        //UPLOADING THE RELIEF REQUEST FORM
        Ion.with(this)
                .load(url)
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
