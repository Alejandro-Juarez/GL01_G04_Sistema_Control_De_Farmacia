/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.Ubicacion;
import java.util.List;

/**
 *
 * @author david
 */
public interface IUbicacionDAO {
    //Metodo para cargar alertas de la Base de datos
    List<Ubicacion> CargarAlertasStock() throws Exception;
}
