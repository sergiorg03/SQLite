package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Insertar extends AppCompatActivity {

    MainActivity MA;
    public static DBManager DDBBM;
    private String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        MA = new MainActivity();
        DDBBM = new DBManager(this, "NombreDB_Prueba", 1/*Version de la BBDD*/);
        sql = "";
    }

    /**
     *
     * @return {String} --
     */
    private String creacionDeCadenasInsert(){
        sql = "INSERT INTO "+" VALUES ("+");";
        return sql;
    }

    public void insertarDatos(View view){
        DDBBM.insert(creacionDeCadenasInsert());
    }
}