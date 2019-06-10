package com.lalthanpuiachhangte.mizoramdisastermanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.DashboardActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.User;

public class MainActivity extends AppCompatActivity {

    EditText passwordET;
    EditText userPhoneNo;
    Button loginButton;
    public final static String ipAddress = "http://10.180.243.6:8080";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefEditor;
   // private final String TOPIC = "";//THIS WILLL BE TAKEN FROM THE SHARED PREFERENCE OF THE APP. IT SHOULD BE UNIQUE TO EVERY APP

   // private final String TAG = "JSA-FCM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "Mainacitivuty");
       // usernameET = findViewById(R.id.usernameEt);
        userPhoneNo = findViewById(R.id.phoneNumberEt);
        passwordET = findViewById(R.id.passwordEt);
        loginButton = findViewById(R.id.loginButton);

        //sharedPreferences = getPreferences(MODE_PRIVATE);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String sharedPhone = sharedPreferences.getString("phoneNo","");
        String sharedPassword = sharedPreferences.getString("password","");

        if(!sharedPhone.equals("") && !sharedPassword.equals("")){
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
        }

       /* FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);
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
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "inside onCreate > onComplete");
                    }
                });
        Log.d(TAG, "inside onCreate");*/

    }

    public void LoginClick(View view) {

        loginButton.setVisibility(View.INVISIBLE);

        //AUTHENTICATE THE USER
        //final String username= String.valueOf(usernameET.getText());
        final String phoneNo= String.valueOf(userPhoneNo.getText());
        final String password = String.valueOf(passwordET.getText());

        //ONLY BOTH ARE FILLED
        if(!phoneNo.equals("") && !password.equals("")){
            String url = ipAddress +"/test/"+phoneNo+"/"+password;
            Ion.with(this)
                    .load(url)
                    .as(new TypeToken<User>(){})
                    .setCallback(new FutureCallback<User>() {
                        @Override
                        public void onCompleted(Exception e, User result) {

                            if(result==null){
                                Toast.makeText(getApplicationContext(),"incorrect",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //PUT THE USER DATA IN SHARED PREFERENCE
                                prefEditor = sharedPreferences.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(result);
                                prefEditor.putString("userObject", json);

                                //SAVE THE USER CREDENTIALS
                                //prefEditor.putString("username",username);
                                prefEditor.putString("phoneNo",phoneNo);
                                prefEditor.putString("password",password);
                                prefEditor.commit();

                                Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                startActivity(intent);
                            }
                            loginButton.setVisibility(View.VISIBLE);
                        }


                    });
        }else{
            loginButton.setVisibility(View.VISIBLE);
        }
    }
    public void SignUp(View view) {

        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
