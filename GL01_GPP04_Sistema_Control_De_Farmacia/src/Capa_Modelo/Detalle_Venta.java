/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Modelo;

/**
 *
 * @author Alejandro
 */
public class Detalle_Venta {
    private int idUbicacion;
    private int idLote;
    private int cantidad_unidades_vendida;
    private double precio_unitario;
    
    public Detalle_Venta(int idUbicacion, int idLote, int cantidad, double precioUnitario) {
        this.idUbicacion = idUbicacion;
        this.idLote = idLote;
        this.cantidad_unidades_vendida = cantidad;
        this.precio_unitario = precioUnitario;
    }

    public int getCantidad_unidades_vendida() {
        return cantidad_unidades_vendida;
    }

    public void setCantidad_unidades_vendida(int cantidad_unidades_vendida) {
        this.cantidad_unidades_vendida = cantidad_unidades_vendida;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }
}
