package com.example.crud.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataGudang.class}, version = 1) //disini kita ambil data untuk kedatabase dari entity yang telah dibuat di kelas data sekolah
public abstract class AppDatabase extends RoomDatabase { //di extend ke roomdatabase supaya setiap input data auto masuk
    public abstract DataGudangDAO dao(); //memanggil kelas datasekolah dao dengan var dao untuk memanggil tiap fungsi didalamnya
    private static AppDatabase appDatabase; //untuk memanggil kelas app database

    public static AppDatabase iniDb(Context context){ //fungsi iniDb yang akan disimpan sementara di context
        if (appDatabase == null) //perulangan jika di database belum ada datanya
            //lalu dia akan mengecek dengan (tempat sementaranya si context, kelas ini, dan nama databasenya)
            //allowMainThreadQueries() ini sebuah method supaya kita bisa menjalankan method read all data.
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "gudangdb").allowMainThreadQueries().build();
        return appDatabase;
    }

    //membuang suatu objek yang dirasa tidak diperukan dalam class ini
    public static void destroyInstance(){
        appDatabase = null; }
}
