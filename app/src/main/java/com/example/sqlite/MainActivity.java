package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase db;
    public static DBManager DDBBM;
    public String comando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DDBBM = new DBManager(this, "NombreDB_Prueba", 1/*Version de la BBDD*/);
        db = DDBBM.getWritableDatabase();
    }

    public void insertarDatosBoton(View view){
        Intent i = new Intent(this, Insertar.class);
        startActivity(i);
        finish();
    }

    public void modificarDatosBoton(View view){
        DDBBM.modificarDatos(comando);
    }

    public void borrarDatosBoton(View view){
        DDBBM.borradoDatos(comando);
    }


}