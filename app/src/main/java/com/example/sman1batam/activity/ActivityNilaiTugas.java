package com.example.sman1batam.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sman1batam.R;
import com.example.sman1batam.adapter.AdapterNilai;
import com.example.sman1batam.api.ApiClient;
import com.example.sman1batam.model.JadwalUjian;
import com.example.sman1batam.model.Nilai;
import com.example.sman1batam.model.ResponseModel;
import com.example.sman1batam.session.PrefSetting;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityNilaiTugas extends AppCompatActivity {

    private RecyclerView rvJadwalUji;
    private RecyclerView.Adapter adJadwalUji;
    private RecyclerView.LayoutManager ImJadwalUji;
    private List<Nilai> listNilai = new ArrayList<>();
    private Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_ujian);

        toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);

        rvJadwalUji = findViewById(R.id.rcView2);
        ImJadwalUji = new LinearLayoutManager(this);
        rvJadwalUji.setLayoutManager(ImJadwalUji);

        PrefSetting prefSetting = new PrefSetting(this);
        SharedPreferences s = prefSetting.getSharePreferences();
        String idUser = s.getString("id", null);
        getNilai(idUser);
    }

    private void getNilai(String idUser) {
        Call<ResponseModel> modelCall = ApiClient.getApi().getNilai(idUser);
        modelCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel respon = response.body();
                if (respon.getSuccess() == 1)
                    Toast.makeText(ActivityNilaiTugas.this, respon.getMassage(), Toast.LENGTH_SHORT).show();

                listNilai = response.body().nilaiTugas;
                adJadwalUji = new AdapterNilai(ActivityNilaiTugas.this, listNilai);
                rvJadwalUji.setAdapter(adJadwalUji);
                adJadwalUji.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ActivityNilaiTugas.this, "Erorr :" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}