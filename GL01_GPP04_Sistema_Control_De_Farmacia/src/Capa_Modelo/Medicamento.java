/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Modelo;

/**
 *
 * @author Alejandro
 */
public class Medicamento {
    private String nombre_medicamento;
    private String descripcion_medicamento;
    private String tipo_medicamento;
    private boolean requiere_receta;
    private int unidades_por_caja;
    private String unidad_medida;
    private float concentracion;
    private float presentacion;

    public String getNombre_medicamento() {
        return nombre_medicamento;
    }

    public void setNombre_medicamento(String nombre_medicamento) {
        this.nombre_medicamento = nombre_medicamento;
    }

    public String getDescripcion_medicamento() {
        return descripcion_medicamento;
    }

    public void setDescripcion_medicamento(String descripcion_medicamento) {
        this.descripcion_medicamento = descripcion_medicamento;
    }

    public String getTipo_medicamento() {
        return tipo_medicamento;
    }

    public void setTipo_medicamento(String tipo_medicamento) {
        this.tipo_medicamento = tipo_medicamento;
    }

    public boolean isRequiere_receta() {
        return requiere_receta;
    }

    public void setRequiere_receta(boolean requiere_receta) {
        this.requiere_receta = requiere_receta;
    }

    public int getUnidades_por_caja() {
        return unidades_por_caja;
    }

    public void setUnidades_por_caja(int unidades_por_caja) {
        this.unidades_por_caja = unidades_por_caja;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public float getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(float concentracion) {
        this.concentracion = concentracion;
    }

    public float getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(float presentacion) {
        this.presentacion = presentacion;
    }
}
