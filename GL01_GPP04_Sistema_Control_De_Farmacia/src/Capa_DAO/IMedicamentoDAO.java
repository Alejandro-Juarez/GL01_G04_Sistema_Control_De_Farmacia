/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.Medicamento;
import java.util.List;

/**
 *
 * @author david
 */
public interface IMedicamentoDAO {
    //Metodo para cargar los medicamentos con su stock
    List<Medicamento> sotckMedicamentos() throws Exception;
    
    //Metodo que devuelve la ubicacion con el medicamento seleccionado mas proximo a vencer
    String ActualizarUbicacion(int MedicamentoS) throws Exception;
    
    //Metodo que devuelve el id lote del medicamendo seleccionado
    int SetIdLote(int MedicamentoS) throws Exception;
    
    //Metodo que devuelve el id ubicacion del medicamento seleccdionado
    int SetIdUbicacion(int MedicamentoS) throws Exception; 
    
    //Carga los tipos de Medicamento y los devuelve en una lista
    List<Medicamento> DatosCb() throws Exception;
    
    //Metodo que Carga el resumen de los totales de venta por categoria de medicamento
    List<Medicamento> CargarResumenVentas(String Seleccion) throws Exception;
    
}
