package com.example.sqlite;

import android.database.sqlite.SQLiteDatabase;

public class InsercionDatosBBDD {

    public static SQLiteDatabase db;

    public InsercionDatosBBDD(SQLiteDatabase db){
        this.db = db;
    }

    public void insert(String comando){
        if (db != null) {
            // Hacer las operaciones que queramos sobre la base de datos
            db.execSQL(comando); // Introduccion de datos en la BBDD
        }
    }


}