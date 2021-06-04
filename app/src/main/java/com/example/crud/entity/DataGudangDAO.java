package com.example.crud.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao //untuk ngasih tau kalau ini kelas DAO (data access object)
public interface DataGudangDAO {
    @Insert //fungsi insert
    long insertData(DataGudang dataGudang); //nantinya dia akan ambil dari kelas data sekolah

    @Query("Select * from gudang_db") //untuk memanggil seluruh data yg ada di db
    List<DataGudang> getData(); //si db itu ngambil datanya dari list yang ada di data sekolah

    @Update //fungsi edit data
    int updateData(DataGudang item);

    @Delete //fungsi hapus data
    void deleteData(DataGudang item);
}

