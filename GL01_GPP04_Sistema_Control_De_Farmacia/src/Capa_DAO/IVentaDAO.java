/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.Venta;

/**
 *
 * @author david
 */
public interface IVentaDAO {
    
    //Metodo que regresa un boolean si el registro de una venta en la BD fue exitosa
    boolean registrarVentaTransaccional(Venta nuevaVenta) throws Exception;
}
