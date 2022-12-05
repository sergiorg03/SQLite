package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.sql.SQLException;

public class Modificar extends AppCompatActivity {

    MainActivity MA;
    public static DBManager DDBBM;
    private String sql;
    private int var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        MA = new MainActivity();
        try {
            DDBBM = new DBManager(this, "NombreDB_Prueba", 1/*Version de la BBDD*/);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "";
    }

    private String creacionDeCadenasUpdate(){
        sql = "UPDATE "+" SET "+" WHERE = "+ this.var +";";
        return sql;
    }

    public void modificarDatos(View view){
        DDBBM.insert(creacionDeCadenasUpdate());
    }
}