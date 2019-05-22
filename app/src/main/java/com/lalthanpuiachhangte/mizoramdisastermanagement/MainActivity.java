package com.lalthanpuiachhangte.mizoramdisastermanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lalthanpuiachhangte.mizoramdisastermanagement.AfterLogin.DashboardActivity;
import com.lalthanpuiachhangte.mizoramdisastermanagement.Entity.User;

public class MainActivity extends AppCompatActivity {

    EditText usernameET,passwordET;
    Button loginButton;
    public final static String ipAddress = "http://10.180.243.9:8080";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = findViewById(R.id.usernameEt);
        passwordET = findViewById(R.id.passwordEt);
        loginButton = findViewById(R.id.loginButton);

        //sharedPreferences = getPreferences(MODE_PRIVATE);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String sharedUsername = sharedPreferences.getString("username","");
        String sharedPassword = sharedPreferences.getString("password","");

        if(!sharedUsername.equals("") && !sharedPassword.equals("")){
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
        }
    }

    public void LoginClick(View view) {

        loginButton.setVisibility(View.INVISIBLE);

        //AUTHENTICATE THE USER
        final String username= String.valueOf(usernameET.getText());
        final String password = String.valueOf(passwordET.getText());

        //ONLY BOTH ARE FILLED
        if(!username.equals("") && !password.equals("")){
            String url = ipAddress +"/test/"+username+"/"+password;
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
                                prefEditor.putString("username",username);
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
