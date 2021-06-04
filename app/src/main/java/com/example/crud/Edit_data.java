package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud.main.LihatDataActivity;
import com.example.crud.main.MainContact;
import com.example.crud.adapter.GudangAdapter;
import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataGudang;
import com.example.crud.presenter.GudangPresenter;

public class Edit_data extends AppCompatActivity implements MainContact.view {
    private AppDatabase appDatabase;
    private GudangPresenter gudangPresenter;
    private GudangAdapter gudangAdapter;
    private EditText etNama, etHarga, etStock;
    private Button btnSubmit;
    private String setNama, setHarga, setStock ;
    private boolean edit = false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        etNama = findViewById(R.id.et_nama);
        etHarga = findViewById(R.id.et_harga);
        etStock = findViewById(R.id.et_stock);


        btnSubmit = findViewById(R.id.btn_submit);
        gudangPresenter = new GudangPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        setNama = getIntent().getStringExtra("nama");
        setHarga = getIntent().getStringExtra("harga");
        setStock = getIntent().getStringExtra("stock");


        id = getIntent().getIntExtra("id", 99);

        etNama.setText(setNama);
        etHarga.setText(setHarga);
        etStock.setText(setStock);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void resetForm() {
        etStock.setText("");
        etNama.setText("");
        etHarga.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LihatDataActivity.class));
    }

    @Override
    public void editData(DataGudang item) {
        etNama.setText(item.getNama_barang());
        etHarga.setText(item.getHarga_barang());
        etStock.setText(item.getStock_barang());

        edit = true;
        btnSubmit.setText("Update");
    }

    @Override
    public void onClick(View v) {
        String  NamaBarang, HargaBarang, StockBarang;
        NamaBarang = etNama.getText().toString();
        HargaBarang = etHarga.getText().toString();
        StockBarang = etStock.getText().toString();
        if(v ==  btnSubmit){
            if(NamaBarang.equals("") || HargaBarang.equals("") || StockBarang.equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {

                gudangPresenter.editData(NamaBarang, HargaBarang, StockBarang, id, appDatabase);
                edit = false;
            }
            resetForm();
        }
    }
}

