/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Modelo;

/**
 *
 * @author Alejandro
 */
public class Empleado {
    private String nombre_empleado;
    private String nombre_apellido;
    private String cargo_empleado;
    private String turno_empleado;
    private int idEmpleado;
    
    public Empleado(){
        
    }
    
    public Empleado(int idEmpleado, String nombre_empleado, String apellidos, String turno) {
        this.idEmpleado = idEmpleado;
        this.nombre_empleado = nombre_empleado;
        this.nombre_apellido = apellidos;
        this.turno_empleado = turno;
    }

    // Método de utilidad para obtener el nombre completo en Java
    public String getNombreCompleto() {
        return this.nombre_empleado + " " + this.nombre_apellido;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getNombre_apellido() {
        return nombre_apellido;
    }

    public void setNombre_apellido(String nombre_apellido) {
        this.nombre_apellido = nombre_apellido;
    }

    public String getCargo_empleado() {
        return cargo_empleado;
    }

    public void setCargo_empleado(String cargo_empleado) {
        this.cargo_empleado = cargo_empleado;
    }

    public String getTurno_empleado() {
        return turno_empleado;
    }

    public void setTurno_empleado(String turno_empleado) {
        this.turno_empleado = turno_empleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
}
