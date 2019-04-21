package com.example.locus;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        final EditText editTextNameR = (EditText) findViewById(R.id.etNameR);
        final EditText editTextUsernameR = (EditText) findViewById(R.id.etUsernameR);
        final EditText editTextPasswordR = (EditText) findViewById(R.id.etPasswordR);
        final EditText editTextEmailR = (EditText) findViewById(R.id.etEmailR);
        final EditText editTextPhoneR = (EditText) findViewById(R.id.etPhoneNumberR);
        final Button buttonRegister = (Button) findViewById(R.id.bRegister);


        final AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
        builder.setCancelable(true);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final CognitoUserAttributes userAttributes = new CognitoUserAttributes();

        final SignUpHandler signupCallback = new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, boolean signUpConfirmationState, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                Log.i(TAG, "sign up successful: " + signUpConfirmationState);
                if (!signUpConfirmationState){
                    Log.i(TAG, "sign up successful but not confirmed, verification code sent to: " + cognitoUserCodeDeliveryDetails.getDestination());
                    builder.setTitle("Locus");
                    builder.setMessage("Success! Check your email for the verification code.");
                    builder.show();
                    Intent verifyIntent = new Intent(RegistrationActivity.this, VerificationActivity.class);
                    RegistrationActivity.this.startActivity(verifyIntent);
                }
                else {
                    Log.i(TAG, "sign up successful");
                }
            }

            @Override
            public void onFailure(Exception exception) {
                builder.setTitle("Locus");
                builder.setMessage("The registration form you entered is incorrect.");
                builder.show();
                Log.i(TAG, "sign up failure:" + exception.getLocalizedMessage());
            }
        };

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(String.valueOf(editTextNameR.getText()).matches("") || String.valueOf(editTextUsernameR.getText()).matches("")|| String.valueOf(editTextPasswordR.getText()).matches("")|| String.valueOf(editTextEmailR.getText()).matches("") || String.valueOf(editTextPhoneR.getText()).matches("")) {
                    builder.setTitle("Locus");
                    builder.setMessage("The registration form you entered is incorrect: empty field(s).");
                    builder.show();
                }
                else{
                    userAttributes.addAttribute("given_name", String.valueOf(editTextNameR.getText()));
                    userAttributes.addAttribute("email", String.valueOf(editTextEmailR.getText()));
                    userAttributes.addAttribute("phone_number", String.valueOf(editTextPhoneR.getText()));
                    CognitoSettings cognitoSettings = new CognitoSettings(RegistrationActivity.this);
                    cognitoSettings.getUserPool().signUpInBackground(String.valueOf(editTextUsernameR.getText()), String.valueOf(editTextPasswordR.getText()), userAttributes,null, signupCallback);
                }
            }
        });
    }
}
