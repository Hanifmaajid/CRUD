package com.example.crud.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataGudang;

public class MainActivity extends AppCompatActivity {
    //inisiasi edit text, button, string untuk membaca inputan sesuaiin sama layout activity main, dan inisiasi class appdatabase
    private EditText etNama, etHarga, etStock ;
    private Button btnSubmit, btnLihat;
    private String setNama, setHarga, setStock ;

    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //seperti biasa untuk mengambil id dari layout yang dihubungkan dengan variable yang telah dibuat diatas
        etNama = findViewById(R.id.et_nama);
        etHarga = findViewById(R.id.et_harga);
        etStock = findViewById(R.id.et_stock);


        btnSubmit = findViewById(R.id.btn_submit);
        btnLihat = findViewById(R.id.btn_lihat);
        appDatabase = AppDatabase.iniDb(getApplicationContext()); //menginisiaslisasi db
        btnSubmit.setOnClickListener(new View.OnClickListener() { //action perpindahan dari class ini ke class lihat data
            @Override
            public void onClick(View v) {
                input();
                Intent submit = new Intent(getApplicationContext(), LihatDataActivity.class);
                startActivity(submit);
            }
        });
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lihat = new Intent(getApplicationContext(), LihatDataActivity.class);
                startActivity(lihat);
            }
        });
    }
    //fungsi untuk create ke databasenya
    public void input(){
        setNama = etNama.getText().toString();
        setHarga = etHarga.getText().toString();
        setStock = etStock.getText().toString();

        //manggil kelas data sekolah
        final DataGudang dataGudang = new DataGudang();

        dataGudang.setNama_barang(setNama);
        dataGudang.setHarga_barang(setHarga);
        dataGudang.setStock_barang(setStock);

        //memanggil fungsi insert data(variable appdatabase untuk koneksi ke app database, variabel kelas data sekolah untuk masukin data
        //ke databasee terus nanti baru di eksekusi
        new InsertData(appDatabase, dataGudang).execute();
    }
    //class insertdata dengan extendsnya
    //asynctask itu kelas yg disediakan android untuk proses pengambilan/pengiriman yang dilakukan secara background
    class InsertData extends AsyncTask<Void, Void, Long> {
        //inisialisasi appdatabase dan datasekolah
        private AppDatabase database;
        private DataGudang dataGudang;

        //fungsi insert data ini menggunakan kelas kelas yg sudah di inisialisasi
        public InsertData(AppDatabase database, DataGudang dataGudang) {
            this.database = database;
            this.dataGudang = dataGudang;
        }

        //nah ini method yg ada di class asynctask
        //pada method ini proses thread berjalan, dan proses pengiriman/pengambilan datanya
        @Override
        protected Long doInBackground(Void... voids) {
            //dari appdatabase diambil var dao yang adalah class datasekolahdao lalu mengakses fungsi insertdata dengan parameternya datasekolah
            return database.dao().insertData(dataGudang);
        }

        //method ini untuk mengupdate user interface ketika proses doinbackground telah selesai
        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            //ngeluarin teks notif "sukses" untuk waktu yang sebentar, kalau dalam waktu lama diganti aja LENGTH_LONG
            Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();

        }

    }
}
