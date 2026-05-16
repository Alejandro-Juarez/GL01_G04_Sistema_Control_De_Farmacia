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
public class Lote {
    private Date fecha_vencimeinto;
    private Date fecha_fabricacion;
    private String estado_lote;

    public Date getFecha_vencimeinto() {
        return fecha_vencimeinto;
    }

    public void setFecha_vencimeinto(Date fecha_vencimeinto) {
        this.fecha_vencimeinto = fecha_vencimeinto;
    }

    public Date getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(Date fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public String getEstado_lote() {
        return estado_lote;
    }

    public void setEstado_lote(String estado_lote) {
        this.estado_lote = estado_lote;
    }
    
}
