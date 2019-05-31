package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.Officer;
import com.lalthanpuiachhangte.mizoramdisastermanagement.MainActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

public class ZonalOfficerActivity extends AppCompatActivity {

    LinearLayout invisibleLinearLayout;
    TextView zoneNumber, zoneOfficer, zoneNote;
    Button callButton;
    AutoCompleteTextView addressET;
    public String phone="";
    public Location currentLocation=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonal_officer);

        invisibleLinearLayout = findViewById(R.id.invisibleLayout);

        zoneNote = findViewById(R.id.zoneNote);
        zoneNumber = findViewById(R.id.zoneNumber);
        zoneOfficer = findViewById(R.id.zoneOfficer);

        callButton = findViewById(R.id.callButton);

        addressET = findViewById(R.id.addressName);

        invisibleLinearLayout.setVisibility(View.GONE);

        //GET GPS LOCATION
        SmartLocation.with(this).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        //Toast.makeText(context,"Location"+location.getLatitude(),Toast.LENGTH_SHORT).show();
                        //  mLocation[0] = location;
                        //Toast.makeText(context,"Location in array"+mLocation[0].getLatitude(),Toast.LENGTH_SHORT).show();
                        currentLocation = location;
                    }
                });
        //Get the address
        ArrayAdapter<CharSequence> localityAdapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.locality,android.R.layout.select_dialog_item);
        addressET.setThreshold(1);
        addressET.setAdapter(localityAdapter);

        addressET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String location = addressET.getText().toString();
                Log.i("TAG","Location: "+location);

                try {
                    getZoneOfficer(location);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    //gps in address lak tur
    public void zawnnaClick(View view) {

        //GET THE CURRENT LOCATION

      Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
      //  try {

            Log.i("TAG",""+currentLocation.getLatitude());
            double lat =currentLocation.getLatitude();
            double lng = currentLocation.getLongitude();
           // addresses = geocoder.getFromLocation(lat, lng , 1);
    /*      } catch (IOException e) {
            e.printStackTrace();
        }
        String cityName = addresses.get(0).getAddressLine(0);
        String stateName = addresses.get(0).getAddressLine(1);
        String countryName = addresses.get(0).getAddressLine(2);
        String locality = addresses.get(0).getLocality();
        String subloc = addresses.get(0).getSubAdminArea();

        Log.i("TAG","coty "+cityName+" starte "+stateName+" cout "+countryName+" loca "+locality+" subloca "+subloc);
*/


        try {
            addresses = geocoder.getFromLocation(
                    currentLocation.getLatitude(),
                    currentLocation.getLongitude(),
                    // In this sample, get just a single address.
                    1);
            String cityName = addresses.get(0).getSubThoroughfare();
            String locality = addresses.get(0).getAdminArea();
            String subloc = addresses.get(0).getSubAdminArea();
            Log.e("TAG","locaThis: "+ locality +"   sub: " +subloc + "   index:"+cityName);
            Toast.makeText(this,""+addresses.get(0).getAddressLine(0),Toast.LENGTH_SHORT).show();
        } catch (IOException ioException) {
            // Catch network or other I/O problems.
          //  errorMessage = getString(R.string.service_not_available);
            Log.e("TAG",""+ ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
          //  errorMessage = getString(R.string.invalid_lat_long_used);
            Log.e("TAG", ""+ illegalArgumentException);
        }

      /*  // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
            }
            deliverResultToReceiver(SyncStateContract.Constants.FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i(TAG, getString(R.string.address_found));
            deliverResultToReceiver(Constants.SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments));
        }
*/

    }

    public void getZoneOfficer(String location) throws Exception{

        Officer mOfficer = new Officer();
        String mLocation = location;
        String url2 = MainActivity.ipAddress + "/get/"+ mLocation;

        mOfficer.setOfficerLocality(mLocation);
        Log.i("TAG","Location in Officer Object: "+mOfficer.getOfficerLocality());

        //UPLOADING THE RELIEF REQUEST FORM
        Ion.with(this)
                .load(url2)
                .as(new TypeToken<Officer>(){})
                .setCallback(new FutureCallback<Officer>() {
                    @Override
                    public void onCompleted(Exception e, Officer officers) {

                      //  Log.i("TAG","officer name: " + officers.get(0).getOfficerName());
                        if(officers!=null){
                            invisibleLinearLayout.setVisibility(View.VISIBLE);

                            zoneNumber.setText(officers.getOfficerZone());
                            zoneOfficer.setText(officers.getOfficerName());
                            zoneNote.setText("Call ang hmiang");

                            phone = officers.getOfficerContact();
                            invisibleLinearLayout.setVisibility(View.VISIBLE);

                        }else
                            Toast.makeText(getApplicationContext(),"ERROR!",Toast.LENGTH_SHORT).show();


                    }
                });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
                startActivity(callIntent);
            }
        });

        //invisibleLinearLayout.setVisibility(View.VISIBLE);

    }
}
