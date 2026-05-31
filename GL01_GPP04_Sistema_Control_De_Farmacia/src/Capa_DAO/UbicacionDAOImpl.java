/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.Ubicacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class UbicacionDAOImpl implements IUbicacionDAO{

    //Iniciando Conexion
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Farmacia;encrypt=true;trustServerCertificate=true;";
    private final String USER = "Farmacia_GL01_G04";
    private final String PASS = "Admin";
    
    /*Metodo para cargar Alertas de Stock de la BD usando un SP 
    que devuelve una lista del objeto Ubicacion 
    donde se almacenan los datos de las Alertas
    */
    @Override
    public List<Ubicacion> CargarAlertasStock() throws Exception {
        String query = "EXEC sp_ReporteBajoStockCursor";
        
        List<Ubicacion> listaUbis = new ArrayList<>();
        
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(DB_URL, USER, PASS); 
                java.sql.PreparedStatement pstmt = conn.prepareStatement(query); 
                java.sql.ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                Ubicacion ubi = new Ubicacion();
                ubi.setPosicion(rs.getString("Estanteria"));
                ubi.setAviso_stock(rs.getString("Mensaje"));
                ubi.setNivel_urgencia(rs.getString("Nivel"));
                listaUbis.add(ubi);
            }

        }
        return listaUbis;
    }
    
}
