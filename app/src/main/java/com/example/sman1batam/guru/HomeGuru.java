package com.example.sman1batam.guru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.sman1batam.R;
import com.example.sman1batam.activity.ActivityJadwal;
import com.example.sman1batam.activity.ActivityJadwalUjian;
import com.example.sman1batam.session.PrefSetting;
import com.example.sman1batam.session.SessionManager;
import com.example.sman1batam.users.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class HomeGuru extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Toolbar toolbar;
    LinearLayout lytJdw, lytjdwU;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru);

        drawerLayout = findViewById(R.id.drawer_layoutguru);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lytJdw = findViewById(R.id.lytJdw);
        lytjdwU = findViewById(R.id.lytJdwUj);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferences();
        session = new SessionManager(HomeGuru.this);

        prefSetting.isLogin(session, prefs);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        lytJdw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeGuru.this, ActivityJadwal.class);
                startActivity(intent);
            }
        });

        lytjdwU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeGuru.this, ActivityJadwalUjian.class);
                startActivity(intent);
            }
        });
    }
    public void logoutguru(MenuItem item) {
        session.setLogin(false);
        session.setSessid(0);
        startActivity(new Intent(HomeGuru.this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}