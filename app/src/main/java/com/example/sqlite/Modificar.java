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

    int opcion;

    // TextViews
    TextView numeroEmpleadoModif;
    TextView apellidoEmpleadoModif;
    TextView salarioEmpleadoModif;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        MA = new MainActivity();
        try {
            DDBBM = new DBManager(this, "DBSqlite", 1/*Version de la BBDD*/);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "";
        numeroEmpleadoModif = findViewById(R.id.emp_no_modif);
        apellidoEmpleadoModif = findViewById(R.id.ModApellido);
        salarioEmpleadoModif = findViewById(R.id.ModSal);
        error = findViewById(R.id.errores1);
    }

    /**
     *
     * @param view
     * @throws SQLException
     * Modificacion  de los datos
     */
    public void modificarDatos(View view) throws SQLException {
        if (isNumeric(String.valueOf(numeroEmpleadoModif))){
            if (DDBBM.comprobacionExistencia(numeroEmpleadoModificar)){
                DDBBM.modificarDatos(datoAModif(datoRellenado()), this.numeroEmpleadoModificar, Double.parseDouble(String.valueOf(this.salarioEmpleadoModif)),
                                                this.apellidoEmpleadoModif.toString(), opcion);
                //Cambiamos de clase
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }else{
                error.setText("El empleado introducido no existe. ");
            }
        }else{
            error.setText("Introduzca el numero de empleado correcto. ");
        }
    }

    /**
     *
     * @param cadena {String} -- Recibe la cadena que se va a comprobar si es un numero
     * @return {boolean} -- Devuelve true si la cadena es un numero, false si es una cadena.
     */
    private static boolean isNumeric(String cadena){
        boolean num = false;
        try {
            numeroEmpleadoModificar = Integer.parseInt(cadena);
            num = true;
        } catch (NumberFormatException nfe){
            num = false;
        }
        return num;
    }

    /**
     *
     * @return
     * Comprueba si los datos estan rellenos
     */
    public int datoRellenado(){
        int num;
        if (apellidoEmpleadoModif == null){
            num = 1;
        }else num = 2;
        return num;
    }

    /**
     *
     * @param opcion
     * @return {String}
     * Realiza el update
     */
    public String datoAModif(int opcion){
        String cadena = null;
        switch (opcion) {
            case 1:
                cadena = "UPDATE Empleados SET apellido = (?) WHERE emp_no = (?);";
                break;
            case 2:
                cadena = "UPDATE Empleados SET salario = "+ this.salarioEmpleadoModif +" WHERE emp_no = "+ this.numeroEmpleadoModificar +";";
                break;
        }
        return cadena;
    }

    public void volver (View view){
        onBackPressed();
    }
}