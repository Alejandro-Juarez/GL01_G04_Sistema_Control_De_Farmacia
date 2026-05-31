/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Modelo;

import java.util.List;
import java.util.Date;

/**
 *
 * @author Alejandro
 */
public class Venta {
    private Date fecha_compra;
    private String metodo_pago;
    private List<Detalle_Venta> carrito;
    private int idEmpleado;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public Venta(int idEmpleado, String metodoPago, List<Detalle_Venta> carrito) {
        this.idEmpleado = idEmpleado;
        this.metodo_pago = metodoPago;
        this.carrito = carrito;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public List<Detalle_Venta> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Detalle_Venta> carrito) {
        this.carrito = carrito;
    }
}
