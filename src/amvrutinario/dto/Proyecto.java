/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amvrutinario.dto;

/**
 *
 * @author Daniel
 */
public class Proyecto {

    private Integer id;
    private String ingenieroResidente;
    private String ingenieroAuxiliar;
    private String sectorAdministrativo;
    private String direccionOficina;
    private String telefono;
    private String celular;
    private String fax;
    private String correo;
    private String amv;
    private String cta;
    private String tipoTerreno;
    private String longitud;
    private String prInicial;
    private String prFinal;
    private String sector;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIngenieroResidente() {
        return ingenieroResidente;
    }

    public void setIngenieroResidente(String ingenieroResidente) {
        this.ingenieroResidente = ingenieroResidente;
    }

    public String getIngenieroAuxiliar() {
        return ingenieroAuxiliar;
    }

    public void setIngenieroAuxiliar(String ingenieroAuxiliar) {
        this.ingenieroAuxiliar = ingenieroAuxiliar;
    }

    public String getSectorAdministrativo() {
        return sectorAdministrativo;
    }

    public void setSectorAdministrativo(String sectorAdministrativo) {
        this.sectorAdministrativo = sectorAdministrativo;
    }

    public String getDireccionOficina() {
        return direccionOficina;
    }

    public void setDireccionOficina(String direccionOficina) {
        this.direccionOficina = direccionOficina;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAmv() {
        return amv;
    }

    public void setAmv(String amv) {
        this.amv = amv;
    }

    public String getCta() {
        return cta;
    }

    public void setCta(String cta) {
        this.cta = cta;
    }

    public String getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(String tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getPrInicial() {
        return prInicial;
    }

    public void setPrInicial(String prInicial) {
        this.prInicial = prInicial;
    }

    public String getPrFinal() {
        return prFinal;
    }

    public void setPrFinal(String prFinal) {
        this.prFinal = prFinal;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

}
