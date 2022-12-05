package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Borrar extends AppCompatActivity {

    MainActivity MA;
    public static DBManager DDBBM;
    private String sql;
    private int var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);
        MA = new MainActivity();
        DDBBM = new DBManager(this, "NombreDB_Prueba", 1/*Version de la BBDD*/);
        sql = "";
    }

    private String creacionDeCadenasDelete(){
        sql = "DELETE FROM "+" WHERE emp_no = "+ this.var +";";
        return sql;
    }

    public void BorrarDatos(){
        DDBBM.insert(creacionDeCadenasDelete());
    }
}