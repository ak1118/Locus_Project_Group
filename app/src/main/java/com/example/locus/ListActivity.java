package com.example.locus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class ListActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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
