package com.example.sman1batam.session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.sman1batam.guru.HomeGuru;
import com.example.sman1batam.siswa.HomeSiswa;

public class PrefSetting {

    public static String name;
    public static String email;
    public static String role;
    Activity activity;

    public PrefSetting(Activity activity){
        this.activity = activity;
    }

    public SharedPreferences getSharePreferences(){
        SharedPreferences pref = activity.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        return pref;
    }

    public void isLogin(SessionManager session, SharedPreferences pref){
        session = new SessionManager(activity);
        if (session.isLoggedIn()){
            name = pref.getString("name","");
            email = pref.getString("email","");
            role= pref.getString("role","");
        }else {
            session.setLogin(false);
            session.setSessid(0);
            Intent intent = new Intent(activity, activity.getClass());
            activity.startActivity(intent);
            activity.finish();
        }
    }
    public void checkLogin(SessionManager session,SharedPreferences pref){
        session = new SessionManager(activity);
        name = pref.getString("name","");
        email = pref.getString("email","");
        role= pref.getString("role","");
        if (session.isLoggedIn()){
            if (role.equals("1")){
                Intent intent = new Intent(activity, HomeGuru.class);
                activity.startActivity(intent);
                activity.finish();
            }else {
                Intent intent = new Intent(activity, HomeSiswa.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }
    }

    public void storeRegIdSharedPreferences(Context context, String name,
                                            String email, String role, SharedPreferences pref){

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("role", role);
        editor.commit();
    }
}
