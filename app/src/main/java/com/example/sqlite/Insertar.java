package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.sql.SQLException;

public class Insertar extends AppCompatActivity {

    MainActivity MA;
    public static DBManager DDBBM;
    private String sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        MA = new MainActivity();
        try {
            DDBBM = new DBManager(this, "NombreDB_Prueba", 1/*Version de la BBDD*/);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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