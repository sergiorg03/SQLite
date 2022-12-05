package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.SQLException;

public class Borrar extends AppCompatActivity {

    MainActivity MA;
    public static DBManager DDBBM;
    private String sql;
    private int var;
    TextView id;
    TextView error3;
    String cadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);
        MA = new MainActivity();
        try {
            DDBBM = new DBManager(this, "NombreDB_Prueba", 1/*Version de la BBDD*/);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "";
        this.id = findViewById(R.id.Emp_noBorrar);
        this.error3 = findViewById(R.id.errores3);
        cadena = String.valueOf(id);
    }

    private String creacionDeCadenasDelete(){
        sql = "DELETE FROM "+" WHERE emp_no = "+ this.var +";";
        return sql;
    }

    public void BorrarDatos(View view){
        DDBBM.insert(creacionDeCadenasDelete());
    }

    public boolean comprobaciones() throws SQLException {
        if (isNumeric(cadena)){
            int numero = Integer.parseInt(cadena);
            if (DDBBM.comprobacionExistencia(numero)){
                return true;
            }else{
                error3.setText("El empleado introducido no existe, por favor introduzca un empleado correcto. ");
                return false;
            }
        }else{
            error3.setText("El dato introducido es incorrecto, por favor introduzca un numero. ");
            return false;
        }
    }

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}