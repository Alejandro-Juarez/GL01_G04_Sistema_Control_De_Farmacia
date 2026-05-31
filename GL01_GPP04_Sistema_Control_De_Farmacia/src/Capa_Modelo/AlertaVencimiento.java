/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Modelo;

import java.util.Date;

/**
 *
 * @author david
 */
public class AlertaVencimiento {
    private String medicamento;
    private int lote;
    private Date fechaVencimiento;
    private String estanteria;
    private String tipoUbicacion;
    private int cantidadDisponible;
    private int diasFaltantes;
    private int totalEntradas;
    private int totalSalidas;
    private double totalVentas;
    private double totalCompras;

    public AlertaVencimiento() {}

    public AlertaVencimiento(int totalEntradas, int totalSalidas, double totalVentas, double totalCompras) {
        this.totalEntradas = totalEntradas;
        this.totalSalidas = totalSalidas;
        this.totalVentas = totalVentas;
        this.totalCompras = totalCompras;
    }

    public AlertaVencimiento(String medicamento, int lote, Date fechaVencimiento, String estanteria, String tipoUbicacion, int cantidadDisponible, int diasFaltantes) {
        this.medicamento = medicamento;
        this.lote = lote;
        this.fechaVencimiento = fechaVencimiento;
        this.estanteria = estanteria;
        this.tipoUbicacion = tipoUbicacion;
        this.cantidadDisponible = cantidadDisponible;
        this.diasFaltantes = diasFaltantes;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(String estanteria) {
        this.estanteria = estanteria;
    }

    public String getTipoUbicacion() {
        return tipoUbicacion;
    }

    public void setTipoUbicacion(String tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getDiasFaltantes() {
        return diasFaltantes;
    }

    public void setDiasFaltantes(int diasFaltantes) {
        this.diasFaltantes = diasFaltantes;
    }

    public int getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(int totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public int getTotalSalidas() {
        return totalSalidas;
    }

    public void setTotalSalidas(int totalSalidas) {
        this.totalSalidas = totalSalidas;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public double getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(double totalCompras) {
        this.totalCompras = totalCompras;
    }
}
