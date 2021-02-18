/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amvrutinario.dto;

import java.util.List;

/**
 *
 * @author Daniel
 */
public class Indicador {

    private Integer id;
    private String tipoInforme;
    private String prInicio;
    private String prFin;
    private Integer horasTrabajadas;
    private Integer obreros;
    private String totalHoras;
    private Integer cantidadEjecutada;
    private String equipo;
    private String rendimiento;
    private String observacion;
    private String tipoIndicador;
    private List<String> climas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(String tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public String getPrInicio() {
        return prInicio;
    }

    public void setPrInicio(String prInicio) {
        this.prInicio = prInicio;
    }

    public String getPrFin() {
        return prFin;
    }

    public void setPrFin(String prFin) {
        this.prFin = prFin;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public Integer getObreros() {
        return obreros;
    }

    public void setObreros(Integer obreros) {
        this.obreros = obreros;
    }

    public String getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(String totalHoras) {
        this.totalHoras = totalHoras;
    }

    public Integer getCantidadEjecutada() {
        return cantidadEjecutada;
    }

    public void setCantidadEjecutada(Integer cantidadEjecutada) {
        this.cantidadEjecutada = cantidadEjecutada;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(String tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public List<String> getClimas() {
        return climas;
    }

    public void setClimas(List<String> climas) {
        this.climas = climas;
    }
}
