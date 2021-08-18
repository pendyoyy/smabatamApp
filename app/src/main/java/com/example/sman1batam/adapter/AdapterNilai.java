package com.example.sman1batam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sman1batam.R;
import com.example.sman1batam.model.Nilai;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterNilai extends RecyclerView.Adapter<AdapterNilai.HolderJadwal> {
    private Context context;
    private List<Nilai> listNilai;

    public AdapterNilai(Context context, List<Nilai> data) {
        this.context = context;
        this.listNilai = data;
    }

    @NotNull
    @Override
    public HolderJadwal onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_nilai, parent, false);
        HolderJadwal holder = new HolderJadwal(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NotNull HolderJadwal holder, int position) {

        holder.tvid.setText(String.valueOf(listNilai.get(position).getId()));
        holder.txtKode.setText("" + listNilai.get(position).getNilai());
        holder.txtNama.setText(listNilai.get(position).getMapel().getName());

    }

    @Override
    public int getItemCount() {
        return listNilai.size();
    }

    public class HolderJadwal extends RecyclerView.ViewHolder {
        TextView tvid, txtKode, txtNama, txtDetail;

        public HolderJadwal(@NotNull View itemView) {
            super(itemView);

            tvid = itemView.findViewById(R.id.tv_id);
            txtKode = itemView.findViewById(R.id.txtKode);
            txtNama = itemView.findViewById(R.id.txtNama);

        }

    }

}
