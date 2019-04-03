package com.example.locus;

import android.content.Context;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Regions;

public class CognitoSettings {
    private String userPoolID = "us-east-1_YOX8C9E5w";
    private String clientID = "2g18j1f1cgelgrheqnd430lf4d";
    private String clientSecret = "k7utcqiok9pqti6kqe9grfvki2q4nt7ivdu6kjsrh5l332f3arb";
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
