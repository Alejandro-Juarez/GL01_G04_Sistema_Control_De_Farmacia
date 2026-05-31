/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.Medicamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class MedicamentoDAOImpl implements IMedicamentoDAO{
    //Iniciando Conexion
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Farmacia;encrypt=true;trustServerCertificate=true;";
    private final String USER = "Farmacia_GL01_G04";
    private final String PASS = "Admin";

    
    /*Metodo que realiza un query a la BD para cargar una vista 
    con el stock de los Medicamentos y los retorna en una lista
    */
    @Override
    public List<Medicamento> sotckMedicamentos() throws Exception {
        List<Medicamento> listaMedicamentos = new ArrayList<>();
        
        String query = "SELECT * FROM vw_Medicamentos_Stock;";
        
         try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                PreparedStatement pstmt = conn.prepareStatement(query); 
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Medicamento med = new Medicamento();
                med.setId_Medicameto(rs.getInt("ID_MEDICAMENTO"));
                med.setNombre_medicamento(rs.getString("MEDICAMENTO"));
                med.setCantidad_actual(rs.getInt("Cantidad Actual"));
                med.setPresentacion(rs.getString("PRESENTACION"));
                
                listaMedicamentos.add(med);
            }
            
        } 
        return listaMedicamentos;
    }
    
    /*Metodo que devuelve un String con la ubicacion con donde el medicamento
    seleccionado esta mas proximo a vencer
    */
    @Override
    public String ActualizarUbicacion(int MedicamentoS) throws SQLException{
        String respuesta;
        
        String queryFEFO = "SELECT TOP 1 u.POSICION, u.Descripcion "
                + "FROM LOTE l "
                + "JOIN DETALLE_UBICACION du ON l.ID_LOTE = du.ID_LOTE "
                + "JOIN UBICACION u ON du.ID_UBICACION = u.ID_UBICACION "
                + "WHERE l.ID_MEDICAMENTO = ? AND du.CANTIDAD_UNIDADES_ALMACENADA > 0 "
                + "ORDER BY l.FECHA_VENCIMIENTO ASC;";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
             java.sql.PreparedStatement pst = conn.prepareStatement(queryFEFO)) {

            pst.setInt(1, MedicamentoS);
            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {

               respuesta =  rs.getString("DESCRIPCION") + "-" + rs.getString("POSICION");
            } else {
                return "Error: Lote físico no encontrado";
            }
                
                
        }
        return respuesta;
        
    }
    
    /*Metodo que devuelve el Id Lote del medicamento seleccionado
    por medio de una query a la BD y lo retorna como un entero
    */
        @Override
    public int SetIdLote(int MedicamentoS) throws SQLException{
        
        String queryFEFO = "SELECT TOP 1 l.ID_LOTE "
                + "FROM LOTE l "
                + "JOIN DETALLE_UBICACION du ON l.ID_LOTE = du.ID_LOTE "
                + "JOIN UBICACION u ON du.ID_UBICACION = u.ID_UBICACION "
                + "WHERE l.ID_MEDICAMENTO = ? AND du.CANTIDAD_UNIDADES_ALMACENADA > 0 "
                + "ORDER BY l.FECHA_VENCIMIENTO ASC;";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
             java.sql.PreparedStatement pst = conn.prepareStatement(queryFEFO)) {

            pst.setInt(1, MedicamentoS);
            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID_LOTE");
            } else {
                return 0;
            }
   
        }       
    }
    
    
    /*Metodo que devuelve el Id Ubicacion del medicamento seleccionado
    por medio de una query a la BD y lo retorna como un entero
    */
        @Override
    public int SetIdUbicacion(int MedicamentoS) throws SQLException{
        
        String queryFEFO = "SELECT TOP 1 u.ID_UBICACION "
                + "FROM LOTE l "
                + "JOIN DETALLE_UBICACION du ON l.ID_LOTE = du.ID_LOTE "
                + "JOIN UBICACION u ON du.ID_UBICACION = u.ID_UBICACION "
                + "WHERE l.ID_MEDICAMENTO = ? AND du.CANTIDAD_UNIDADES_ALMACENADA > 0 "
                + "ORDER BY l.FECHA_VENCIMIENTO ASC;";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
             java.sql.PreparedStatement pst = conn.prepareStatement(queryFEFO)) {

            pst.setInt(1, MedicamentoS);
            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("ID_UBICACION");
            } else {
                return 0;
            }
        
            
                
        }       
    }
    
    /*Metodo que devuelve una lista de medicamentos con los dintintos tipos
    de medicamentos existentes por medio de una query a la DB
    */
    @Override
    public List<Medicamento> DatosCb() throws Exception {
        String query = "SELECT DISTINCT TIPO_MEDICAMENTO FROM MEDICAMENTO WHERE TIPO_MEDICAMENTO IS NOT NULL ORDER BY TIPO_MEDICAMENTO ASC;";
        List<Medicamento> listaCat = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            Medicamento med = new Medicamento();
            med.setTipo_medicamento(rs.getString("TIPO_MEDICAMENTO"));
            listaCat.add(med);
        }
        
    }
        return listaCat;
    }

    /*Metodo que devuelve una lista de Medicamentos con sus totales de venta 
    por categoria dependiendo de la categoria seleccionada
    haciendolo por medio de querys a la BD
    */
    @Override
    public List<Medicamento> CargarResumenVentas(String categoriaSeleccionada) throws Exception {
        List<Medicamento> ListaMeds = new ArrayList<>();
        
    if (categoriaSeleccionada.equals("Todas las categorías")) {
        
        String queryTodas = "SELECT DISTINCT TIPO_MEDICAMENTO, " +
                            "dbo.fn_TotalVentasPorCategoria(TIPO_MEDICAMENTO) AS GananciaTotal " +
                            "FROM MEDICAMENTO WHERE TIPO_MEDICAMENTO IS NOT NULL;";
                            
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(queryTodas);
             ResultSet rs = pst.executeQuery()) {
             
            
            while (rs.next()) {
                Medicamento med = new Medicamento();
                
                med.setTipo_medicamento(rs.getString("TIPO_MEDICAMENTO"));
                med.setGanancia_total(rs.getDouble("GananciaTotal"));
                ListaMeds.add(med);
            }
        } 
        return ListaMeds;
    } 
    else {
        //Si se selecciona una Categoria en Concreto
        String queryUna = "SELECT dbo.fn_TotalVentasPorCategoria(?) AS TotalGenerado;";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(queryUna)) {
             
            pst.setString(1, categoriaSeleccionada);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Medicamento med = new Medicamento();
                
                med.setTipo_medicamento(categoriaSeleccionada);
                med.setGanancia_total(rs.getDouble("TotalGenerado"));
                ListaMeds.add(med);
                

            }
        } 
        return ListaMeds;
    }
    }
    
    
    
}
