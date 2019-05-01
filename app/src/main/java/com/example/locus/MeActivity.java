package com.example.locus;

import android.content.Intent;
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

public class MeActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        CognitoSettings cognitoSettings = new CognitoSettings(MeActivity.this);
        CognitoUserPool userPool = cognitoSettings.getUserPool();
        CognitoUser user = userPool.getCurrentUser();

        final TextView textViewNameMe = (TextView) findViewById(R.id.tvNameMe);
        final TextView textViewEmailMe = (TextView) findViewById(R.id.tvEmaiMe);
        final TextView textViewPhoneMe = (TextView) findViewById(R.id.tvPhoneMe);

        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav_me);
        mMainNav.getMenu().getItem(2).setChecked(true);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_x :
                        Intent bIntent = new Intent(MeActivity.this, business.class);
                        MeActivity.this.startActivity(bIntent);
                        return true;

                    case R.id.nav_y :
                        return true;


                    case R.id.nav_z:
                        Intent zIntent = new Intent(MeActivity.this, FirstLayerActivity.class);
                        MeActivity.this.startActivity(zIntent);
                        return true;

                    case R.id.nav_me :
                        return true;

                }
                return false;
            }
        });

        //final NavigationMenuItemView view = (NavigationMenuItemView) findViewById(R.id.nav_me);
        //view.setEnabled(true);

        GetDetailsHandler handler = new GetDetailsHandler() {
            @Override
            public void onSuccess(final CognitoUserDetails list) {
                Map mDetails = list.getAttributes().getAttributes();
                String name = mDetails.get("given_name").toString();
                String eMail = mDetails.get("email").toString();
                String phone_number = mDetails.get("phone_number").toString();
                textViewNameMe.setText("Name:  " + name);
                textViewEmailMe.setText("Email:  " + eMail);
                textViewPhoneMe.setText("Phone:  " + phone_number);

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
