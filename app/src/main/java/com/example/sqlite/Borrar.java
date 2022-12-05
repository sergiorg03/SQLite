package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.SQLException;

public class Borrar extends AppCompatActivity {

    MainActivity MA;
    public static DBManager DDBBM;
    private String sql;
    private int emp_noVal;
    TextView id;
    TextView error3; // Textview para mostrar los errores que ocurran
    String cadena;
    static int numeroEmpleadoABorrar;

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

    private String creacionDeCadenasDelete() {
        sql = "DELETE FROM Empleados WHERE emp_no = " + this.numeroEmpleadoABorrar + ";";
        return sql;
    }

    public boolean comprobaciones() throws SQLException {
        boolean confirmado = false;
        if (isNumeric(cadena)){
            int numero = Integer.parseInt(cadena);
            if (DDBBM.comprobacionExistencia(numero)){
                confirmado = true;
            }else{
                error3.setText("El empleado introducido no existe, por favor introduzca un empleado correcto. ");
            }
        }else{
            error3.setText("El dato introducido es incorrecto, por favor introduzca un numero. ");
        }
        return confirmado;
    }

    private static boolean isNumeric(String cadena){
        try {

            numeroEmpleadoABorrar = Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public void confirmarBorrado(View view) throws SQLException {
        if (comprobaciones()){
            DDBBM.borradoDatos(creacionDeCadenasDelete());
            Intent i = new Intent (this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void volver (View view){
        onBackPressed();
    }
}