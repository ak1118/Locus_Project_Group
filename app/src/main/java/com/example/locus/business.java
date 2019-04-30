package com.example.locus;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.amplify.generated.graphql.CreateLocation2Mutation;
import com.amazonaws.amplify.generated.graphql.CreateLocationMutation;
import com.amazonaws.amplify.generated.graphql.ListLocationsQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

import type.CreateLocation2Input;
import type.CreateLocationInput;


public class business extends Activity {
    private BottomNavigationView mMainNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        final Button buttonAddBusiness = (Button) findViewById(R.id.bAddBusiness);
        buttonAddBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runMutation();
            }
        });


        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mMainNav.getMenu().getItem(0).setChecked(true);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_x :

                        return true;

                    case R.id.nav_y :
                        return true;


                    case R.id.nav_z:
                        Intent zIntent = new Intent(business.this, FirstLayerActivity.class);
                        business.this.startActivity(zIntent);
                        return true;

                    case R.id.nav_me :
                        Intent meIntent = new Intent(business.this, MeActivity.class);
                        business.this.startActivity(meIntent);
                        return true;

                }
                return false;
            }
        });

    }


    public void runMutation(){
        String name = ((EditText) findViewById(R.id.etBusinessName)).getText().toString();
        String address = ((EditText) findViewById(R.id.etAddress)).getText().toString();
        String deal = ((EditText) findViewById(R.id.etDeal)).getText().toString();

        AWSAppSyncClient awsAppSyncClient = ClientFactory.getInstance(this.getApplicationContext());
        CreateLocation2Input createLocation2Input = CreateLocation2Input.builder()
                .name(name)
                .address(address)
                .lat(40.692769)
                .lon(-73.986277)
                .rating(4.0)
                .deal(deal)
                .build();

        awsAppSyncClient.mutate(CreateLocation2Mutation.builder().input(createLocation2Input).build())
                .enqueue(mutationCallback);
    }

    private GraphQLCall.Callback<CreateLocation2Mutation.Data> mutationCallback = new GraphQLCall.Callback<CreateLocation2Mutation.Data>() {
        @Override
        public void onResponse(@Nonnull Response<CreateLocation2Mutation.Data> response) {
            Log.i("Results", "Added Location2");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("Error", e.toString());
        }
    };



}
