package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase db;
    public static DBManager DDBBM;
    public String comando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            DDBBM = new DBManager(this, "NombreDB_Prueba", 1/*Version de la BBDD*/);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db = DDBBM.getWritableDatabase();
    }

    public void insertarDatosBoton(View view){
        Intent i = new Intent(this, Insertar.class);
        startActivity(i);
        finish();
    }

    public void modificarDatosBoton(View view){
        Intent i = new Intent(this, Modificar.class);
        startActivity(i);
        finish();
    }

    public void borrarDatosBoton(View view){
        Intent i = new Intent(this, Borrar.class);
        startActivity(i);
        finish();
    }


}