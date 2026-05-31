/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_DAO;
import Capa_Modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author david
 */
public class EmpleadoDAOImpl implements IEmpleadoDAO{
    
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Farmacia;encrypt=true;trustServerCertificate=true;";
    private final String USER = "Farmacia_GL01_G04";
    private final String PASS = "Admin";

    @Override
    public List<Empleado> listarTodos() throws Exception {
        List<Empleado> lista = new ArrayList<>();
        
        // Consultamos las columnas limpias directamente de la tabla
        String query = "SELECT ID_EMPLEADO, NOMBRE_EMPLEADO, APELLIDOS_EMPLEADO, TURNO_EMPLEADO FROM EMPLEADO;";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setIdEmpleado(rs.getInt("ID_EMPLEADO"));
                emp.setNombre_empleado(rs.getString("NOMBRE_EMPLEADO"));
                emp.setNombre_apellido(rs.getString("APELLIDOS_EMPLEADO"));
                emp.setTurno_empleado(rs.getString("TURNO_EMPLEADO"));
                
                lista.add(emp);
            }
        }
        return lista;
    }
}
