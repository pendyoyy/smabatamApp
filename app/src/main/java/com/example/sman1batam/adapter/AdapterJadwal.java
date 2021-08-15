package com.example.sman1batam.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sman1batam.R;
import com.example.sman1batam.View.ViewJadwalHarian;
import com.example.sman1batam.model.Jadwal;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterJadwal extends RecyclerView.Adapter<AdapterJadwal.HolderJadwal> {
    private Context context;
    private List<Jadwal> listJadwal;

    public AdapterJadwal(Context context, List<Jadwal> listJadwal){
        this.context = context;
        this.listJadwal = listJadwal;
    }

    @NotNull
    @Override
    public HolderJadwal onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_jadwal, parent, false);
        HolderJadwal holder = new HolderJadwal(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NotNull HolderJadwal holder, int position) {

        holder.tvid.setText(String.valueOf(listJadwal.get(position).getId()));
        holder.txtKode.setText(listJadwal.get(position).getKodeJadwal());
        holder.txtNama.setText(listJadwal.get(position).getNamaJadwal());

        holder.txtDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.txtDetail.getContext(), ViewJadwalHarian.class);
                intent.putExtra("namaJadwal",listJadwal.get(position).getNamaJadwal());
                intent.putExtra("document", "http://smabatam30.ddns.net/smabatam/public/storage/jadwal/"+listJadwal.get(position).getDocument());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.txtDetail.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listJadwal.size();
    }

    public class HolderJadwal extends RecyclerView.ViewHolder{
        TextView tvid, txtKode, txtNama,txtDetail;

        public HolderJadwal(@NotNull View itemView){
            super(itemView);

            tvid = itemView.findViewById(R.id.tv_id);
            txtKode = itemView.findViewById(R.id.txtKode);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtDetail = itemView.findViewById(R.id.txtDetail);

        }

    }

}
