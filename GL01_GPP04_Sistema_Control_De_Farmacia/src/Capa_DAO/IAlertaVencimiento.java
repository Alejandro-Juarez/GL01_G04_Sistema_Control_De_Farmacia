/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.AlertaVencimiento;
import java.util.List;

/**
 *
 * @author david
 */
public interface IAlertaVencimiento {
    
    //Metodo Para cargar Medicamentos por Vencer
    List<AlertaVencimiento> listarAlertasPorVencer() throws Exception;
    
    //Metodo para cargar Resumen de Totales
    AlertaVencimiento CargarDatosResumen() throws Exception;
    
    //Metodo que determina si la eliminacion de medicamentos fue exitosa
    boolean VaciarStockVencido(int id_Lote) throws Exception;
}
