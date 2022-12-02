package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static SQLiteDatabase db;
    private static CreacionBBDD dbHelper;
    InsercionDatosBBDD insertarDatos;
    public String comando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new CreacionBBDD(this, "NombreDB_Prueba", 1/*Version de la BBDD*/);
        db = dbHelper.getWritableDatabase();
        insertarDatos = new InsercionDatosBBDD(db);
    }

    public void insertarDatosBoton(View view){
        insertarDatos.insert(comando);
    }

}