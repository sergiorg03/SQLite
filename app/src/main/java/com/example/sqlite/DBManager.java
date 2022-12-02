package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {

    public SQLiteDatabase db;

    private static final String createTable = "CREATE TABLE comments(_id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, comment TEXT)";

    /**
     *
     * @param context {Context} -- Clase desde la que se le llama
     * @param name {String} -- Nombre de la BBDD
     * @param version {int} -- Version de la Base de datos
     *
     * Este método se deja por defecto.
     */

    public DBManager(@Nullable Context context, @Nullable String name, @Nullable int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //En el método onCreate creamos las tablas de la BBDD
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Migra de una version antigua a una nueva de la BBDD
    }

    /**
     *
     * @param comando {String} -- Recibe la cadena de los datos a modificar
     */
    public void modificarDatos (String comando) {
        db.execSQL(comando);
    }

    /**
     *
     * @param comando {String} -- Recibe la cadena de los datos a borrar
     */
    public void borradoDatos (String comando) {
        db.execSQL(comando);
    }

    /**
     *
     * @param comando {String} -- Recibe la cadena de los datos a insertar
     */
    public void insert(String comando){
        if (db != null) {
            // Hacer las operaciones que queramos sobre la base de datos
            db.execSQL(comando); // Introduccion de datos en la BBDD
        }
    }
}
