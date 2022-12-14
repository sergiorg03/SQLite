package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager extends SQLiteOpenHelper {

    /**
     * Creacion de la base de datos junto a sus campos.
     */
    private static final String createTableEmple = "CREATE TABLE Empleados(emp_no INTEGER PRIMARY KEY AUTOINCREMENT, apellido text, salario double)";
    // Variables
    public SQLiteDatabase db;
    String cadcon = "jdbc:mysql://localhost/";
    String bd = "Empleados";
    String user = "root";
    String password = "MANAGER";
    // Creamos la conexion y los statements para las sentencias sql a ejecutar.
    Connection conexion;
    Statement sentencia;
    PreparedStatement pstmt;
    ResultSet resultado;

    /**
     * @param context {Context} -- Clase desde la que se le llama
     * @param name    {String} -- Nombre de la BBDD
     * @param version {int} -- Version de la Base de datos
     *                Este método se deja por defecto.
     */
    public DBManager(@Nullable Context context, @Nullable String name, @Nullable int version) throws SQLException {
        super(context, name, null, version);
    }

    /**
     * @param sqLiteDatabase -{SQLiteDatabase} -- Se crea las tablas de la BBDD
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Creamos las tablas de la BBDD en caso de estar vacía
        if (db == null) {
            sqLiteDatabase.execSQL(createTableEmple);
            try {
                conexion = DriverManager.getConnection(cadcon + bd, user, password); //Creamos la conexion con la base de datos
                sentencia = conexion.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Migra de una version antigua a una nueva de la BBDD
    }

    /**
     * @param comando {String} -- Recibe la cadena de los datos a modificar
     */
    public void modificarDatos(String comando, int codEmple, double salario, String apellido, int opcion) {
        if (db != null) {
            // Hacer las operaciones que queramos sobre la base de datos
            try {
                pstmt = conexion.prepareStatement(comando);

                /*
                 * Asignamos variables a su placeholder
                 */

                switch (opcion){
                    case 1:
                        pstmt.setString (1, apellido);
                        pstmt.setInt(2, codEmple);
                        break;
                    case 2:
                        pstmt.setDouble(1, salario);
                        pstmt.setInt(2, codEmple);
                        break;
                }

                // Ejecutamos la sentencia sql
                int lineasAfectadas = pstmt.executeUpdate();
                System.out.println(lineasAfectadas);

            } catch (SQLException sqle) {
                System.out.println("No se puede introducir los datos en la base de datos. ");

            }
        }
    }

    /**
     * @param comando {String} -- Recibe la cadena de los datos a borrar
     */
    public void borradoDatos(String comando, int codEmple) {
        if (db != null) {
            // Hacer las operaciones que queramos sobre la base de datos
            try {
                pstmt = conexion.prepareStatement(comando);

                /*
                 * Asignamos variables a su placeholder
                 */
                pstmt.setInt(1, codEmple);

                // Ejecutamos la sentencia sql
                int lineasAfectadas = pstmt.executeUpdate();
                System.out.println(lineasAfectadas);

            } catch (SQLException sqle) {
                System.out.println("No se puede introducir los datos en la base de datos. ");

            }
        }
    }

    /**
     * @param comando {String} -- Recibe la cadena de los datos a insertar
     */
    public void insert(String comando, String nombre, double salario) {
        if (db != null) {
            // Hacer las operaciones que queramos sobre la base de datos
            try {
                pstmt = conexion.prepareStatement(comando);

                /*
                 * Asignamos variables a su placeholder
                 */
                pstmt.setString(1, nombre);
                pstmt.setDouble(2, salario);

                // Ejecutamos la sentencia sql
                int lineasAfectadas = pstmt.executeUpdate();
                System.out.println(lineasAfectadas);

            } catch (SQLException sqle) {
                System.out.println("No se puede introducir los datos en la base de datos. ");

            }
        }
    }

    /**
     * @param emp_no
     * @return
     * @throws SQLException Comprueba la existencia de un dato en concreto en la tabla
     */
    public boolean comprobacionExistencia(int emp_no) {
        boolean valor = false;
        try {
            resultado = sentencia.executeQuery("SELECT emp_no FROM Empleados WHERE emp_no = " + emp_no + ";");
            if (resultado.next()) {
                valor = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
    }
}