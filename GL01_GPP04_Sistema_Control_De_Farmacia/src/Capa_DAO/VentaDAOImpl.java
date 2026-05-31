/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.Detalle_Venta;
import Capa_Modelo.Venta;
import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author david
 */
public class VentaDAOImpl implements IVentaDAO{
    
    //Iniciando Conexion
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Farmacia;encrypt=true;trustServerCertificate=true;";
    private final String USER = "Farmacia_GL01_G04";
    private final String PASS = "Admin";
    
    /*Metodo que realiza el registro de una venta utilizando la tabla carrito
    para obtener todos los detalle_venta  y si la transaccion fue exitosa
    retorna true
    */
    @Override
    public boolean registrarVentaTransaccional(Venta nuevaVenta) throws Exception {
        
        //Uso de la tablaDetalles que es un tabla TYPE creada en la BD para el manejo de las Detalle_Venta
        SQLServerDataTable tablaDetalles = new SQLServerDataTable();
        tablaDetalles.addColumnMetadata("ID_UBICACION", java.sql.Types.INTEGER);
        tablaDetalles.addColumnMetadata("ID_LOTE", java.sql.Types.INTEGER);
        tablaDetalles.addColumnMetadata("CANTIDAD", java.sql.Types.INTEGER);
        tablaDetalles.addColumnMetadata("PRECIO_UNITARIO", java.sql.Types.DECIMAL);

        for (Detalle_Venta detalle : nuevaVenta.getCarrito()) {
            tablaDetalles.addRow(
                detalle.getIdUbicacion(), 
                detalle.getIdLote(), 
                detalle.getCantidad_unidades_vendida(), 
                detalle.getPrecio_unitario()
            );
        }

        String sqlCall = "{call dbo.sp_RegistrarVenta(?, ?, ?)}";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             SQLServerCallableStatement cstmt = (SQLServerCallableStatement) conn.prepareCall(sqlCall)) {

            cstmt.setInt(1, nuevaVenta.getIdEmpleado());
            cstmt.setString(2, nuevaVenta.getMetodo_pago());
            cstmt.setStructured(3, "dbo.ListaDetalleVenta", tablaDetalles);
            cstmt.execute();
            return true; 
            
        } 
    }
}
