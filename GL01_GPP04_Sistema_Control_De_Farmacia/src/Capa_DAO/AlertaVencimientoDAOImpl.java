/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.AlertaVencimiento;
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
public class AlertaVencimientoDAOImpl implements IAlertaVencimiento {
    
    //Iniciamos la Conexion
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Farmacia;encrypt=true;trustServerCertificate=true;";
    private final String USER = "Farmacia_GL01_G04";
    private final String PASS = "Admin";

    /*Metodo que realiza una query utilizando una vista creada en la base de datos 
    para Obtener medicamentos Proximos a Vencer y retornar una lista de estas Alertas
    */
    @Override
    public List<AlertaVencimiento> listarAlertasPorVencer() throws Exception {
        List<AlertaVencimiento> listaAlertas = new ArrayList<>();

        String query = "SELECT * FROM vw_Medicamentos_Por_Vencer ORDER BY Dias_Faltantes ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                PreparedStatement pstmt = conn.prepareStatement(query); 
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                AlertaVencimiento alerta = new AlertaVencimiento();

                alerta.setMedicamento(rs.getString("Medicamento"));
                alerta.setLote(rs.getInt("Lote"));
                alerta.setFechaVencimiento(rs.getDate("Fecha_Vencimiento"));
                alerta.setEstanteria(rs.getString("Estanteria"));
                alerta.setTipoUbicacion(rs.getString("Tipo_Ubicacion"));
                alerta.setCantidadDisponible(rs.getInt("Cantidad_Disponible"));
                alerta.setDiasFaltantes(rs.getInt("Dias_Faltantes"));

                listaAlertas.add(alerta);
            }
        }
        return listaAlertas;
    }
    
    /*Se hace una Query a la BD para Obtener los Valores de los Totales
    de Entradas, Salidas, Ventas y Compras para devolverlos como un 
    objeto del tipo AlertaVencimiento
    */
    @Override
    public AlertaVencimiento CargarDatosResumen() throws Exception {
        
        String queryCant = "SELECT \n"
                + "(SELECT SUM(CANTIDAD) FROM MOVIMIENTO\n"
                + "	WHERE OPERACION = '+') AS TotalE, \n"
                + "(SELECT SUM(CANTIDAD) FROM MOVIMIENTO\n"
                + "	WHERE OPERACION = '-')AS TotalS,\n"
                + "(SELECT SUM(PRECIO_CAJA) FROM DETALLE_COMPRA) AS TotalC,\n"
                + "(   SELECT ISNULL(SUM(dv.CANTIDAD_UNIDADES_VENDIDA * dv.PRECIO_UNITARIO),0)\n"
                + "    FROM DETALLE_VENTA dv\n"
                + "    ) AS TotalV;";
       
        AlertaVencimiento resumen = new AlertaVencimiento();
        
        try (java.sql.Connection conn = java.sql.DriverManager.getConnection(DB_URL, USER, PASS); 
            java.sql.PreparedStatement pstmt = conn.prepareStatement(queryCant); 
            java.sql.ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                int totalE = rs.getInt("TotalE");
                int totalS = rs.getInt("TotalS");
                double totalV = rs.getDouble("TotalV");
                double totalC = rs.getDouble("TotalC");
                
                 resumen = new AlertaVencimiento(totalE, totalS, totalV, totalC);
                 return resumen;
            }

        }
         return resumen;       
    }

    /*Metodo booleano que utiliza un SP de la BD para vaciar Stock 
    por vencer o vencido de todas las Ubicaciones
    retorna true si la eliminacion fue exitosa
    */
    @Override
    public boolean VaciarStockVencido(int id_Lote) throws Exception {
        
                String sqlCall = "{call dbo.sp_EliminarLoteVencido(?)}";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sqlCall)) {
            
            pstmt.setInt(1, id_Lote);
            pstmt.execute();
            return true;

        } 
        
    }
}
