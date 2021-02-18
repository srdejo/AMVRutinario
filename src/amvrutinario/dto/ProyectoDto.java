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
public class ProyectoDto {
    
    private Proyecto proyecto;
    private List<Indicador> indicadores;
    private Impresion impresion;

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<Indicador> getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(List<Indicador> indicadores) {
        this.indicadores = indicadores;
    }

    public Impresion getImpresion() {
        return impresion;
    }

    public void setImpresion(Impresion impresion) {
        this.impresion = impresion;
    }
    
    
}
