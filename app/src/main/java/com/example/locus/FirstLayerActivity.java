package com.example.locus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;

import java.util.Map;

public class FirstLayerActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_layer);

        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        CognitoSettings cognitoSettings = new CognitoSettings(FirstLayerActivity.this);
        CognitoUserPool userPool = cognitoSettings.getUserPool();
        CognitoUser user = userPool.getCurrentUser();

        final TextView textViewName = (TextView) findViewById(R.id.tvNameFirst);


        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_x :
                        return true;

                    case R.id.nav_y :
                        return true;

                    case R.id.nav_me :
                        return true;

                    case R.id.nav_z:
                        return true;

                }
                return false;
            }
        });
        //Get User Information
        GetDetailsHandler handler = new GetDetailsHandler() {
            @Override
            public void onSuccess(final CognitoUserDetails list) {
                Map mDetails = list.getAttributes().getAttributes();
                String name = mDetails.get("given_name").toString();
                String eMail = mDetails.get("email").toString();
                String phone_number = mDetails.get("phone_number").toString();
                textViewName.setText("name: " + name +"Email: " + eMail + "Phone Number: " + phone_number);
                // Successfully retrieved user details
            }

            @Override
            public void onFailure(final Exception exception) {
                // Failed to retrieve the user details, probe exception for the cause
            }
        };

        user.getDetailsInBackground(handler);
    }
}
