package com.example.crud.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gudang_db") //untuk nama tabelnya

public class DataGudang {
    @NonNull
    @PrimaryKey(autoGenerate = true) //menandakan primary key yaitu id
    @ColumnInfo(name = "id") //info buat atribut dalam database
    private int id; // tipe data dari masing masin gatributnya

    @ColumnInfo(name = "nama_barang")
    private String nama_barang;

    @ColumnInfo(name = "harga_barang")
    private String harga_barang;

    @ColumnInfo(name = "stock_barang")
    private String stock_barang;

    //selanjutnya di alt enter untuk setter dan getternya
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) { this.id = id; }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(String harga_barang) {
        this.harga_barang = harga_barang;
    }

    public String getStock_barang() {
        return stock_barang;
    }

    public void setStock_barang(String stock_barang) {

        this.stock_barang = stock_barang;
    }

}
