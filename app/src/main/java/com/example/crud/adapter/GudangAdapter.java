package com.example.crud.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.Edit_data;
import com.example.crud.entity.DataGudang;
import com.example.crud.main.MainContact;

import java.util.List;

public class GudangAdapter extends RecyclerView.Adapter<GudangAdapter.ViewHolder> {
    Context context;
    List<DataGudang> list;
    MainContact.hapus view;

    public GudangAdapter(Context context, List<DataGudang> list, MainContact.hapus view) {
        this.view = view;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override

    public GudangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_lihat_data, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final DataGudang data = list.get(i);
        viewHolder.tvNama.setText(data.getNama_barang());
        viewHolder.tvHarga.setText(data.getHarga_barang());
        viewHolder.tvStock.setText(data.getStock_barang());
        viewHolder.id.setText(String.valueOf(data.getId())); //disini berbeda karena id itu langsung autogenerate jadi selalu ada nilainya
        viewHolder.btnHapus.setOnClickListener(new View.OnClickListener() { //membuat action hapus
            @Override
            public void onClick(View v) {
                view.deleteData(data); //terlempar ke class maincontact
                // return true;
            }
        });
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() { //membuat action hapus
            @Override
            public void onClick(View v) {
                Intent x = new Intent(context, Edit_data.class); //pertama disini kita melemparkan class dulu dengan menyimpan berbagai data
                x.putExtra("nama", data.getNama_barang()); //data pertama dengan name valuenya nama, dan class data sekolah.ambil nilai dari get yg ada disana
                x.putExtra("harga", data.getHarga_barang());
                x.putExtra("stock", data.getStock_barang());
                x.putExtra("id", data.getId());
                //dia mengirim ke class edit data bahwa akan memulai aktivitas dalam tugas baru yang harus dilakukan
                x.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //proses perpindahan
                context.startActivity(x);
            }
        });

    }

    @Override
    public int getItemCount() { return list.size(); } //mendapatkan ukuran set data yg akan ditampilkan

    public class ViewHolder extends RecyclerView.ViewHolder {
        //inisialisasi text view dan button samain kaya di layout lihat data
        TextView tvNama, tvHarga,tvStock, id;
        Button btnHapus, btnEdit;

        //class holdernya yg menghubungkan antara inisialisasinya ke yg ada pada layout lihat data
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama_item);
            tvHarga = itemView.findViewById(R.id.tv_harga_item);
            tvStock = itemView.findViewById(R.id.tv_stock_item);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            id = itemView.findViewById(R.id.tv_id_item);
        }
    }
}
