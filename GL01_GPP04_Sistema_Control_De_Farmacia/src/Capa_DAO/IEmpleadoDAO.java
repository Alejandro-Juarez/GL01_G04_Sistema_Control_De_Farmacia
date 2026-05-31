/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_DAO;

import Capa_Modelo.Empleado;
import java.util.List;

/**
 *
 * @author david
 */
public interface IEmpleadoDAO {
    //Metodo para listar a todos los empleados
    List<Empleado> listarTodos() throws Exception;
}
