/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.Movimiento;
import java.util.List;

/**
 *
 * @author david
 */
public interface IMovimientoDAO {
    
    //Metodo que carga todos los movimientos y los devuelve en forma de una lista
    List<Movimiento> CargarMovimientos() throws Exception;
    
}
