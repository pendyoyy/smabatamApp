package com.example.sman1batam.siswa;

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

public class HomeSiswa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Toolbar toolbar;
    LinearLayout lytJdwH, lytJdwU;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);

        drawerLayout = findViewById(R.id.drawer_layoutsiswa);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lytJdwH = findViewById(R.id.lytJdwH);
        lytJdwU = findViewById(R.id.lytJdwU);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferences();
        session = new SessionManager(HomeSiswa.this);

        prefSetting.isLogin(session, prefs);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        lytJdwH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeSiswa.this, ActivityJadwal.class);
                startActivity(intent);
            }
        });

        lytJdwU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeSiswa.this, ActivityJadwalUjian.class);
                startActivity(intent);
            }
        });
    }

    public void logout(MenuItem item) {
        session.setLogin(false);
        session.setSessid(0);
        startActivity(new Intent(HomeSiswa.this, LoginActivity.class));
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}