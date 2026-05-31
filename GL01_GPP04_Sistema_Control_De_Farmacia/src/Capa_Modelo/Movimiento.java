/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Modelo;

import java.util.Date;

/**
 *
 * @author Alejandro
 */
public class Movimiento {
    private String tipo_movimiento;
    private  Date fecha_movimiento;
    private String referencia;
    private String operacion;
    private int Cantidad;
    private int id_movimiento;
    
    public Movimiento(){
        
    }

    public Movimiento(String tipo_movimiento, Date fecha_movimiento, String referencia, String operacion, int Cantidad, int id_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
        this.fecha_movimiento = fecha_movimiento;
        this.referencia = referencia;
        this.operacion = operacion;
        this.Cantidad = Cantidad;
        this.id_movimiento = id_movimiento;
    }
    
    

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public Date getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(Date fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }
    
}
