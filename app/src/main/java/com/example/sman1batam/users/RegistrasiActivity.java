package com.example.sman1batam.users;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.sman1batam.model.ResponseModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrasiActivity extends AppCompatActivity {

   private Button btnRegistrasi;
   private TextView btnBackLogin;
   private EditText edtName, edtEmail, edtPassword;

    private ProgressDialog pd;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        edtName =  findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnBackLogin = findViewById(R.id.btnbackLogin);
        btnRegistrasi = findViewById(R.id.btnRegstrasi);

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrasiActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (name.isEmpty()) {
                    edtName.setError("Enter Name");
                    edtName.requestFocus();
                    return;
                }
                if (name.length() < 3) {
                    edtName.setError("Name too Short");
                    edtName.requestFocus();
                    return;
                }
                if (name.length() > 30) {
                    edtName.setError("Name too Long");
                    edtName.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    edtEmail.setError("Enter Email");
                    edtEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    edtEmail.setError("Enter Valid Email");
                    edtEmail.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    edtPassword.setError("Enter Password");
                    edtEmail.requestFocus();
                    return;
                }
                if (password.length() < 8) {
                    edtPassword.setError("Password too Short");
                    edtPassword.requestFocus();
                    return;
                }
                if (password.length() > 30) {
                    edtPassword.setError("Password too Long");
                    edtPassword.requestFocus();
                } else {
                    registrasi(name, email, password);
                }
            }
        });
    }

    public void registrasi(String name, String email, String password) {

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("role", "2");
        params.put("email", email);
        params.put("password", password);
        pd = ProgressDialog.show(this,null,"Mohon Tunggu....!!!",true,false);

        Call<ResponseModel> call = ApiClient.getApi().register(params);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel respon = response.body();
                if (respon.getSuccess() == 1) {
                    pd.dismiss();
                    Toast.makeText(RegistrasiActivity.this, "Berhasil Membuat Akun"+respon.getUser().getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrasiActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    pd.dismiss();
                    Toast.makeText(RegistrasiActivity.this, "Error:"+respon.getMassage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(RegistrasiActivity.this, "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}