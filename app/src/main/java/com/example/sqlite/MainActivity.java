package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase db;
    public static DBManager DDBBS;
    public String comando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DDBBS = new DBManager(this, "NombreDB_Prueba", 1/*Version de la BBDD*/);
        db = DDBBS.getWritableDatabase();
    }

    public void insertarDatosBoton(View view){
        DDBBS.insert(comando);
    }

    public void modificarDatosBoton(View view){
        DDBBS.modificarDatos(comando);
    }

    public void borrarDatosBoton(View view){
        DDBBS.borradoDatos(comando);
    }


}