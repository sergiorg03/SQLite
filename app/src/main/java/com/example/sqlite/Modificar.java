package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class Modificar extends AppCompatActivity {

    MainActivity MA;
    public static DBManager DDBBM;
    private String sql;
    private static int numeroEmpleadoModificar;

    TextView numeroEmpleadoModif;
    TextView apellidoEmpleadoModif;
    TextView salarioEmpleadoModif;

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
        numeroEmpleadoModif = findViewById(R.id.emp_no_modif);
        apellidoEmpleadoModif = findViewById(R.id.ModApellido);
        salarioEmpleadoModif = findViewById(R.id.ModSal);
    }



    public void modificarDatos(View view) throws SQLException {

        if (isNumeric(String.valueOf(numeroEmpleadoModif))){
            if (DDBBM.comprobacionExistencia(numeroEmpleadoModificar)){
                DDBBM.insert(datoAModif(datoRellenado()));
                //Cambiamos de clase
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(this, "El empleado introducido no existe. ", Toast.LENGTH_LONG);
            }
        }else{
            Toast.makeText(this, "Introduzca el numero de empleado correcto. ", Toast.LENGTH_LONG);
        }
    }

    /**
     *
     * @param cadena {String} -- Recibe la cadena que se va a comprobar si es un numero
     * @return {boolean} -- Devuelve true si la cadena es un numero, false si es una cadena.
     */
    private static boolean isNumeric(String cadena){
        try {

            numeroEmpleadoModificar = Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public void volver (View view){
        onBackPressed();
    }

    public int datoRellenado(){
        if (apellidoEmpleadoModif == null){
            return 1;
        }else return 2;
    }

    public String datoAModif(int opcion){

        String cadena = null;
        switch (opcion) {
            case 1:
                cadena = "UPDATE Empleados SET apellido = "+ this.apellidoEmpleadoModif +" WHERE emp_no = "+ this.numeroEmpleadoModificar +";";
                break;
            case 2:
                cadena = "UPDATE Empleados SET salario = "+ this.salarioEmpleadoModif +" WHERE emp_no = "+ this.numeroEmpleadoModificar +";";
                break;
        }
        return cadena;
    }
}