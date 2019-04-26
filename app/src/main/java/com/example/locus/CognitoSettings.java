package com.example.locus;

import android.content.Context;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Regions;

public class CognitoSettings {
    private String userPoolID = "us-east-1_Sc2YimF5b";
    private String clientID = "6dsthpf94odjigs0qpcs90gj0a";
    private String clientSecret = "1bifvpq15g3i6fi0ah7tglv8vqffceo4fd28d9ls55qrkobl0aji";
    private Regions cognitoRegion = Regions.US_EAST_1;
    private Context context;


    public CognitoSettings(Context context){
        this.context = context;
    }
    public String getUserPoolId(){
        return userPoolID;
    }
    public String getClientID(){
        return clientID;
    }
    public String getClientSecret(){
        return clientSecret;
    }
    public Regions getCognitoRegion(){
        return cognitoRegion;
    }
    public CognitoUserPool getUserPool(){
        return new CognitoUserPool(context, userPoolID, clientID, clientSecret, cognitoRegion);
    }
}