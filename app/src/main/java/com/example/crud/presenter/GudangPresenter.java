package com.example.crud.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataGudang;
import com.example.crud.main.MainContact;


public class GudangPresenter implements MainContact.datapresenter{
    MainContact.view view;
    MainContact.hapus viewH;
    public GudangPresenter(MainContact.view view) {
        this.view = view;
    }

    public GudangPresenter(MainContact.hapus viewH) {
        this.viewH = viewH;
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase database;
        private DataGudang dataGudang;
        public EditData(AppDatabase database, DataGudang dataGudang) {
            this.database = database;
            this.dataGudang = dataGudang;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.dao().updateData(dataGudang);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            view.sukses();
        }
    }

    @Override
    public void editData(String nama_barang, String harga_barang, String stock, int id, AppDatabase database) {
        final DataGudang dataGudang = new DataGudang();
        dataGudang.setNama_barang(nama_barang);
        dataGudang.setStock_barang(stock);
        dataGudang.setHarga_barang(harga_barang);
        dataGudang.setId(id);
        new EditData(database, dataGudang).execute();
    }
    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        private DataGudang dataGudang;
        Context context;
        public DeleteData(AppDatabase database, DataGudang dataGudang) {
            this.database = database;
            this.dataGudang = dataGudang;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataGudang);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewH.sukses();
        }

    }
    @Override
    public void deleteData(DataGudang dataGudang, AppDatabase database) {
        new DeleteData(database,dataGudang).execute();
    }

}