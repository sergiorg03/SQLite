package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.SQLException;

public class Insertar extends AppCompatActivity {

    MainActivity MA;
    public static DBManager DDBBM;
    private String sql;
    static double salarioEmpleado;

    TextView apellidos;
    TextView salarios;
    TextView error;

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
        apellidos = findViewById(R.id.Apellido);
        salarios = findViewById(R.id.Salario);
        error = findViewById(R.id.Errores);
    }

    /**
     *
     * @return {String} --
     */
    private String creacionDeCadenasInsert(){
        sql = "INSERT INTO Empleados VALUES ("+apellidos+", "+ salarioEmpleado+");";
        return sql;
    }

    public void insertarDatos(View view){
        if (isNumeric(String.valueOf(salarios))) {
            DDBBM.insert(creacionDeCadenasInsert());
        }else{
            error.setText("El salario introducido no es correcto. Por favor introduzca un salario correcto.");
        }
    }

    public void volver (View view){
        onBackPressed();
    }

    private static boolean isNumeric(String cadena){
        try {

            Integer.parseInt(cadena);
            salarioEmpleado = Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}