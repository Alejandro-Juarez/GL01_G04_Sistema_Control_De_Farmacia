/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alejandro
 */
public class Conexion_BD {

    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=Farmacia;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "Farmacia_GL01_G04"; 
    private static final String PASSWORD = "Admin";
    
    public static Connection conectar() {
        Connection conn = null;
        try {
            // Cargar el Driver de Microsoft SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Establecer conexion
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa a SQL Server");
            
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver JDBC de SQL Server");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la BD");
            e.printStackTrace();
        }
        return conn;
    }
    
    // MÉTODO MAIN INTEGRADO
    public static void main(String[] args) {
        // Ejecutamos la función de conectar
        Connection pruebaConn = Conexion_BD.conectar();
        
        // Verificamos si la conexión no es nula para confirmar el éxito
        if (pruebaConn != null) {
            System.out.println("¡Perfecto! Tu programa Java ya se comunica con la base de datos Farmacia.");
            
            try {
                // Cerramos la conexión de prueba de inmediato (buena práctica)
                pruebaConn.close();
                System.out.println("Conexion de prueba cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion de prueba.");
                e.printStackTrace();
            }
        } else {
            System.out.println("La conexion devolvió NULL. Revisa los errores impresos arriba.");
        }
    }
}