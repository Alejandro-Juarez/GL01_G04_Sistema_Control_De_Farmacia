/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.Movimiento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class MovimientoDAOImpl implements IMovimientoDAO {

    //Iniciando Conexion
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Farmacia;encrypt=true;trustServerCertificate=true;";
    private final String USER = "Farmacia_GL01_G04";
    private final String PASS = "Admin";

    /*Metodo que devuelve una lista de Movimientos 
    obtenidos desde una query de la BD
    */
    @Override
    public List<Movimiento> CargarMovimientos() throws Exception {

        String query = "SELECT TIPO_MOVIMIENTO, CAST(FECHA_MOVIMIENTO AS DATE) AS Fecha, REFERENCIA, CANTIDAD \n"
                + "FROM MOVIMIENTO\n"
                + "ORDER BY FECHA_MOVIMIENTO DESC;";
        List<Movimiento> listaMeds = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                PreparedStatement pstmt = conn.prepareStatement(query); 
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Movimiento mov = new Movimiento();

                mov.setCantidad(rs.getInt("CANTIDAD"));
                mov.setTipo_movimiento(rs.getString("TIPO_MOVIMIENTO"));
                mov.setFecha_movimiento(rs.getDate("Fecha"));
                mov.setReferencia(rs.getString("REFERENCIA"));

                listaMeds.add(mov);
            }

        }
        return listaMeds;

    }

}
