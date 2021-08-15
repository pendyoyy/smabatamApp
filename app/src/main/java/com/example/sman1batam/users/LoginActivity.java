package com.example.sman1batam.users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sman1batam.R;
import com.example.sman1batam.api.ApiClient;
import com.example.sman1batam.guru.HomeGuru;
import com.example.sman1batam.model.ResponseModel;
import com.example.sman1batam.session.PrefSetting;
import com.example.sman1batam.session.SessionManager;
import com.example.sman1batam.siswa.HomeSiswa;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

   private TextView btnRegis;
   private Button btnLogin;
   private EditText edtEmail, edtPassword;
   private ProgressDialog pd;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

        btnRegis = findViewById(R.id.btnRegis);
        btnLogin = findViewById(R.id.btnlogin);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferences();
        session = new SessionManager(this);
        prefSetting.checkLogin(session, prefs);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrasiActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (email.isEmpty())
                {
                    edtEmail.setError("Enter Email");
                    edtEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    edtEmail.setError("Enter Valid Email");
                    edtEmail.requestFocus();
                    return;
                }
                if (password.isEmpty())
                {
                    edtPassword.setError("Enter Password");
                    edtPassword.requestFocus();
                    return;
                }
                if (password.length()<8)
                {
                    edtPassword.setError("Password too Short");
                    edtPassword.requestFocus();
                    return;
                }
                if (password.length()>30)
                {
                    edtPassword.setError("Password too Long");
                    edtPassword.requestFocus();
                }
                else
                {
                    loginUser(email,password);
                }
            }
        });
    }
    public void loginUser(String email, String password){

        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        pd = ProgressDialog.show(this,null,"Mohon Tunggu....!!!",true,false);
        Call<ResponseModel> call = ApiClient.getApi().login(params);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel respon = response.body();
                if (response.body().getSuccess()==1) {
                    pd.dismiss();
                    btnLogin.setEnabled(true);
                    Toast.makeText(getApplicationContext(), respon.getMassage(), Toast.LENGTH_SHORT).show();
                    String role = response.body().getUser().getRole();
                    String name = response.body().getUser().getName();
                    String email = response.body().getUser().getEmail();
                    session.setLogin(true);
                    prefSetting.storeRegIdSharedPreferences(LoginActivity.this , name, email, role, prefs);
                    if(role.equals("1")) {
                        Intent intent = new Intent(LoginActivity.this, HomeGuru.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(LoginActivity.this, HomeSiswa.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                }else{
                    pd.dismiss();
                    Toast.makeText(LoginActivity.this, "Error:"+respon.getMassage(),Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}