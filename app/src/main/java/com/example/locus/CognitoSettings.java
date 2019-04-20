package com.example.locus;

import android.content.Context;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Regions;

public class CognitoSettings {
    private String userPoolID = "us-east-1_ur6bokDS4";
    private String clientID = "1kk0u963edapah0fd8q684su82";
    private String clientSecret = "14mjrk37k2vv3lid6jgrvb2gcpgu077jqm3lmq31is5tu7hsk2s8";
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