package com.example.locus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;

public class VerificationActivity extends AppCompatActivity {

    private CognitoUserPool userPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etVerification = (EditText) findViewById(R.id.etVerificationCode);

        final Button bVerify = (Button) findViewById(R.id.bVerification);
        bVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfirmTask().execute(String.valueOf(etVerification.getText()), String.valueOf(etUsername.getText()));
                Intent mainIntent = new Intent(VerificationActivity.this, MainActivity.class);
                VerificationActivity.this.startActivity(mainIntent);
            }
        });
    }
    private class ConfirmTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String...strings) {
            final String[] result = new String[1];
            final GenericHandler confirmationCallback = new GenericHandler() {
                @Override
                public void onSuccess() {
                    result[0] = "Succeeded!";
                }
                public void onFailure(Exception exception) {
                    result[0] = "Failed" + exception.getMessage();
                }
            };
            CognitoSettings cognitoSettings = new CognitoSettings(VerificationActivity.this);

            CognitoUser thisUser = cognitoSettings.getUserPool().getUser(strings[1]);
            thisUser.confirmSignUp(strings[0], false,confirmationCallback);
            return result[0];
        }

    }
}

