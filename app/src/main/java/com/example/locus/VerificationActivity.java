package com.example.locus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;

public class VerificationActivity extends AppCompatActivity {

    private CognitoUserPool userPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        final EditText editTextUsernameV = (EditText) findViewById(R.id.etUsernameV);
        final EditText editTextVerification = (EditText) findViewById(R.id.etVerificationCode);

        LinearLayout layoutVerification = (LinearLayout) findViewById(R.id.layoutVerificationTwo);
        Animation downToUp = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        layoutVerification.setAnimation(downToUp);

        final AlertDialog.Builder builder = new AlertDialog.Builder(VerificationActivity.this);
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

        final Button bVerify = (Button) findViewById(R.id.bVerification);
        bVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(editTextUsernameV.getText()).matches("") || String.valueOf(editTextVerification.getText()).matches("")) {
                    builder.setTitle("Locus");
                    builder.setMessage("The verification code or username you entered is incorrect: empty field(s)");
                    builder.show();
                }
                else {
                    new ConfirmTask().execute(String.valueOf(editTextVerification.getText()), String.valueOf(editTextUsernameV.getText()));
                    builder.setTitle("Locus");
                    builder.setMessage("The verification code or username you entered is incorrect.");
                    builder.show();
                }
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
                    Intent mainIntent = new Intent(VerificationActivity.this, LoginActivity.class);
                    VerificationActivity.this.startActivity(mainIntent);
                }
                public void onFailure(Exception exception) {

                }
            };
            CognitoSettings cognitoSettings = new CognitoSettings(VerificationActivity.this);

            CognitoUser thisUser = cognitoSettings.getUserPool().getUser(strings[1]);
            thisUser.confirmSignUp(strings[0], false,confirmationCallback);
            return result[0];
        }

    }
}
