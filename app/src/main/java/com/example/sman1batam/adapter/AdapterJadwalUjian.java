package com.example.sman1batam.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sman1batam.R;
import com.example.sman1batam.View.ViewJadwalUjian;
import com.example.sman1batam.model.JadwalUjian;

import java.util.List;

public class AdapterJadwalUjian extends RecyclerView.Adapter<AdapterJadwalUjian.HolderJadwalUjian> {
    private Context context;
    private List<JadwalUjian> listJadwalUji;


    public AdapterJadwalUjian(Context context, List<JadwalUjian> listJadwalUji){
        this.context = context;
        this.listJadwalUji = listJadwalUji;
    }

    @NonNull
    @Override
    public HolderJadwalUjian onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_jadwal_ujian, parent, false);
        return new HolderJadwalUjian(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderJadwalUjian holder, int position) {
        holder.tvid1.setText(String.valueOf(listJadwalUji.get(position).getId()));
        holder.txtKodeUji.setText(listJadwalUji.get(position).getKodeJadwalUjian());
        holder.txtNamaUji.setText(listJadwalUji.get(position).getNamaJadwalUjian());
        holder.txtTgl.setText(listJadwalUji.get(position).getUpdatedAt());

        holder.txtDetailUji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewJadwalUjian.class);
                intent.putExtra("namaJadwalUjian",listJadwalUji.get(position).getNamaJadwalUjian());
                intent.putExtra("filePdf","http://smabatam30.ddns.net/smabatam/public/storage/jadwalujian/"+listJadwalUji.get(position).getFilePdf());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listJadwalUji.size();
    }

    public class HolderJadwalUjian extends RecyclerView.ViewHolder{
        TextView tvid1, txtKodeUji, txtNamaUji, txtDetailUji, txtTgl;

        public HolderJadwalUjian(@NonNull View itemView){
            super(itemView);

            tvid1 = itemView.findViewById(R.id.tv_id2);
            txtKodeUji = itemView.findViewById(R.id.txtKodeUji);
            txtNamaUji = itemView.findViewById(R.id.txtNamaUji);
            txtTgl= itemView.findViewById(R.id.txtTgl);
            txtDetailUji = itemView.findViewById(R.id.txtDetailUji);


        }

    }
}
