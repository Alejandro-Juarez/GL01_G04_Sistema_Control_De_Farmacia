/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Modelo;

/**
 *
 * @author Alejandro
 */
public class Ubicacion {
    private String tipo_ubicacion;
    private String posicion;
    private String descripcion;
    private int id_ubicacion;
    private String aviso_stock;
    private String nivel_urgencia;
    
    public Ubicacion(){
    
    }

    public Ubicacion(String tipo_ubicacion, String posicion, String descripcion, int id_ubicacion, String aviso_stock, String nivel_urgencia) {
        this.tipo_ubicacion = tipo_ubicacion;
        this.posicion = posicion;
        this.descripcion = descripcion;
        this.id_ubicacion = id_ubicacion;
        this.aviso_stock = aviso_stock;
        this.nivel_urgencia = nivel_urgencia;
    }

    public String getTipo_ubicacion() {
        return tipo_ubicacion;
    }

    public void setTipo_ubicacion(String tipo_ubicacion) {
        this.tipo_ubicacion = tipo_ubicacion;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getAviso_stock() {
        return aviso_stock;
    }

    public void setAviso_stock(String aviso_stock) {
        this.aviso_stock = aviso_stock;
    }

    public String getNivel_urgencia() {
        return nivel_urgencia;
    }

    public void setNivel_urgencia(String nivel_urgencia) {
        this.nivel_urgencia = nivel_urgencia;
    }
    
}
