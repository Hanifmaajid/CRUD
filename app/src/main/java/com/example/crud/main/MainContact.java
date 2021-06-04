package com.example.crud.main;

import android.view.View;

import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataGudang;

import java.util.List;

//untuk mengaktifkan fungsi edit dan delete
public interface MainContact {
    interface view extends View.OnClickListener{
        void resetForm();
        void sukses();
        //void getData(List<DataGudang> list);
        void editData(DataGudang item);
        //void deleteData(DataGudang item);
    }
    interface datapresenter{
        //readData(AppDatabase database);
        void editData(String nama_barang, String harga_barang, String stock_barang, int id, AppDatabase database);
        void deleteData(DataGudang dataDiri, AppDatabase database);
    }
    interface Cetak extends View.OnClickListener{
        void getData(List<DataGudang> list);
    }
    interface hapus{
        // void resetForm();
        void sukses();
        void deleteData(DataGudang item);
    }
}
