package com.example.sman1batam.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sman1batam.R;
import com.example.sman1batam.adapter.AdapterJadwal;
import com.example.sman1batam.api.ApiClient;
import com.example.sman1batam.model.Jadwal;
import com.example.sman1batam.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityJadwal extends AppCompatActivity {

    private RecyclerView rvJadwal;
    private RecyclerView.Adapter adJadwal;
    private RecyclerView.LayoutManager ImJadwal;
    private List<Jadwal> listJadwal = new ArrayList<>();
    private Toolbar toolbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        toolbar1 =findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);

        rvJadwal = findViewById(R.id.rcView);
        ImJadwal = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvJadwal.setLayoutManager(ImJadwal);
        getJadwal();

    }

    private void getJadwal(){
        Call<ResponseModel> calljadwal = ApiClient.getApi().getjadwal();
        calljadwal.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel respon = response.body();
                if (respon.getSuccess()==1)
                    Toast.makeText(ActivityJadwal.this, respon.getMassage(), Toast.LENGTH_SHORT).show();

                listJadwal = response.body().getJadwals();
                adJadwal = new AdapterJadwal(ActivityJadwal.this,listJadwal);
                rvJadwal.setAdapter(adJadwal);
                adJadwal.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ActivityJadwal.this, "Erorr :"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}