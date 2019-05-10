package com.example.locus;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private List<String> businesses = new ArrayList<String>();
    private List<String> deals = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        if(intent.getStringExtra("name")!= null && intent.getStringExtra("name").length() > 0) {

            businesses.add(intent.getStringExtra("name"));
            deals.add(intent.getStringExtra("deal"));

            LinearLayout layout = (LinearLayout) findViewById(R.id.List);
            TextView valueTV = new TextView(getApplicationContext());
            valueTV.setText(intent.getStringExtra("name"));
            valueTV.setId(4);
            valueTV.setTextColor(Color.parseColor("#FFFFFF"));
            valueTV.setBackgroundResource(R.drawable.background_login_two);
            valueTV.setPadding(5,5,5,5);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            params.setMargins(0,10,0,10);
            valueTV.setLayoutParams(params);
            layout.addView(valueTV);

            TextView rating = new TextView(getApplicationContext());
            rating.setText("Rating: 5");
            rating.setBackgroundResource(R.drawable.background_login);
            rating.setPadding(5,5,5,5);
            params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            params.setMargins(0,10,0,10);
            rating.setLayoutParams(params);
            layout.addView(rating);

            TextView deal = new TextView(getApplicationContext());
            String dealText = "Deal: " + intent.getStringExtra("deal");
            deal.setText(dealText);
            deal.setBackgroundResource(R.drawable.background_login);
            deal.setPadding(10,10,10,10);
            params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            params.setMargins(0,10,0,10);
            deal.setLayoutParams(params);
            layout.addView(deal);
        }


        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav_list);
        mMainNav.getMenu().getItem(2).setChecked(true);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_x :
                        Intent xIntent = new Intent(ListActivity.this, business.class);
                        ListActivity.this.startActivity(xIntent);
                        return true;

                    case R.id.nav_z:
                        Intent zIntent = new Intent(ListActivity.this, FirstLayerActivity.class);
                        ListActivity.this.startActivity(zIntent);
                        return true;

                    case R.id.nav_me :
                        Intent meIntent = new Intent(ListActivity.this, MeActivity.class);
                        ListActivity.this.startActivity(meIntent);
                        return true;

                    case R.id.nav_y :
                        return true;

                }
                return false;
            }
        });



    }
}
