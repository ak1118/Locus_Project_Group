package com.example.locus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;

public class RegistrationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);

        final CognitoUserAttributes userAttributes = new CognitoUserAttributes();

        final SignUpHandler signUpHandler = new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, boolean signUpConfirmationState, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                Intent mainIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                RegistrationActivity.this.startActivity(mainIntent);
            }

            @Override
            public void onFailure(Exception exception) {
                Intent mainIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                RegistrationActivity.this.startActivity(mainIntent);
            }
        };

        final Button bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                userAttributes.addAttribute("given_name", String.valueOf(etName.getText()));
                userAttributes.addAttribute("email", String.valueOf(etEmail.getText()));

                CognitoSettings cognitoSettings = new CognitoSettings(RegistrationActivity.this);
                cognitoSettings.getUserPool().signUp(String.valueOf(etUsername.getText()), String.valueOf(etPassword.getText()), userAttributes, null, signUpHandler);
            }
        });
    }
}
