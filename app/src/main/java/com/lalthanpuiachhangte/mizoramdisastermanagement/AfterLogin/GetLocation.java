package com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

public class GetLocation extends Activity {


    //your common method
    public static Location getLocation(Context context){

        //TODO task
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
}
