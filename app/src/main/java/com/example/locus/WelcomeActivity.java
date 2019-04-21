package com.example.locus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final Button buttonLogin = (Button) findViewById(R.id.buttonToLogin);
        final Button buttonJoin = (Button) findViewById(R.id.buttonToJoin);
        LinearLayout llinearLayoutOne = (LinearLayout) findViewById(R.id.layoutOne);
        Animation uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        llinearLayoutOne.setAnimation(uptodown);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                WelcomeActivity.this.startActivity(registerIntent);
            }
        });

        buttonJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(WelcomeActivity.this, RegistrationActivity.class);
                WelcomeActivity.this.startActivity(registerIntent);
            }
        });

    }
}
