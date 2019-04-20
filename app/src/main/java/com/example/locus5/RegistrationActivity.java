package com.example.locus5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);

        final CognitoUserAttributes userAttributes = new CognitoUserAttributes();

        final SignUpHandler signUpCallback = new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, boolean signUpConfirmationState, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                Log.i(TAG, "sign up successful: " + signUpConfirmationState);
                if (!signUpConfirmationState){
                    Log.i(TAG, "sign up successful but not confirmed, verification code sent to: " + cognitoUserCodeDeliveryDetails.getDestination());
                    Intent verifyIntent = new Intent(RegistrationActivity.this, VerificationActivity.class);
                    RegistrationActivity.this.startActivity(verifyIntent);
                }
                else {
                    Log.i(TAG, "sign up successful");
                }
            }

            @Override
            public void onFailure(Exception exception) {
                Intent verifyIntent = new Intent(RegistrationActivity.this, VerificationActivity.class);
                RegistrationActivity.this.startActivity(verifyIntent);
                Log.i(TAG, "sign up failure:" + exception.getLocalizedMessage());

            }
        };

        final Button bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                userAttributes.addAttribute("given_name", String.valueOf(etName.getText()));
                userAttributes.addAttribute("email", String.valueOf(etEmail.getText()));

                CognitoSettings cognitoSettings = new CognitoSettings(RegistrationActivity.this);
                cognitoSettings.getUserPool().signUpInBackground(String.valueOf(etUsername.getText()), String.valueOf(etPassword.getText()), userAttributes,null, signUpCallback);
            }
        });
    }
}
