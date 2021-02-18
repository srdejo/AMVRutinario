/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amvrutinario;

import amvrutinario.dto.Indicador;
import amvrutinario.dto.ProyectoDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class PDF {

    private final String nombre;
    private final ProyectoDto proyectoDto;
    private final com.itextpdf.text.Font arial = FontFactory.getFont("Arial", 10, Font.PLAIN, BaseColor.BLACK);

    public PDF(String nombre, ProyectoDto proyectoDto) {
        this.nombre = "./" + nombre + ".pdf";
        this.proyectoDto = proyectoDto;
    }

    private PdfPTable cabecera(PdfWriter writer) {
        PdfPTable cabecera = new PdfPTable(3);
        cabecera.setWidthPercentage(110);
        cabecera.getDefaultCell().setPadding(2);
        try {
            cabecera.setWidths(new float[]{20, 60, 30});
        } catch (DocumentException ex) {
            Logger.getLogger(Imprimir.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (Objects.nonNull(this.proyectoDto.getImpresion().getUrlImagen())) {
                Image imagen = Image.getInstance(this.proyectoDto.getImpresion().getUrlImagen());
                imagen.setAlignment(Image.ALIGN_CENTER);
                imagen.scaleAbsolute(60, 80);
                PdfPCell cellImagen = new PdfPCell(imagen);
                cellImagen.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellImagen.setRowspan(5);
                cabecera.addCell(cellImagen);
            } else {
                PdfPCell vacio = new PdfPCell();
                vacio.setRowspan(5);
                cabecera.addCell(vacio);
            }
        } catch (IOException | DocumentException ex) {
            ex.getMessage();
        }

        cabecera.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        cabecera.addCell("Ministerio de Transporte");
        Phrase paginacion = new Phrase(String.format("page %s of %s", String.valueOf(writer.getCurrentPageNumber()), String.valueOf(writer.getPageNumber())));
        paginacion.getFont().setSize(8);

        cabecera.addCell(paginacion);
        cabecera.addCell("INSTITUTO NACIONAL DE VÌAS");
        cabecera.addCell("");
        cabecera.addCell("SECRETARÍA GENERAL TÉCNICA");
        if (Objects.nonNull(this.proyectoDto.getImpresion().getFechaImpresion())) {
            SimpleDateFormat formatFecha = new SimpleDateFormat("EEE, d MMM yyyy");
            Phrase fecha = new Phrase("Fecha: " + formatFecha.format(this.proyectoDto.getImpresion().getFechaImpresion()));
            fecha.getFont().setSize(8);
            cabecera.addCell(fecha);
        } else {
            cabecera.addCell("");
        }
        cabecera.addCell("TERRITORIAL NORTE DE SANTANDER");

        Phrase nro_infome = new Phrase("Informe No: " + this.proyectoDto.getImpresion().getNumeroInforme());
        nro_infome.getFont().setSize(8);
        cabecera.addCell(nro_infome);

        PdfPCell cellAMV = new PdfPCell(new Phrase("INFORME DE AMV G4"));
        cellAMV.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cellAMV.setHorizontalAlignment(Element.ALIGN_CENTER);

        cabecera.addCell(cellAMV);

        Phrase nro_contrato = new Phrase("Contrato No: " + this.proyectoDto.getImpresion().getNumeroContrato());
        nro_contrato.getFont().setSize(8);
        cabecera.addCell(nro_contrato);

        PdfPCell cellTemp = new PdfPCell(new Phrase("EJECUTAR ES NUESTRA RUTA"));
        cellTemp.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTemp.setColspan(3);
        cabecera.addCell(cellTemp);

        return cabecera;
    }

    private PdfPTable generalidades() {
        PdfPTable generalidades = new PdfPTable(2);
        generalidades.setWidthPercentage(100);
        generalidades.getDefaultCell().setPadding(5);
        try {
            generalidades.setWidths(new float[]{40, 60});
        } catch (DocumentException ex) {
            Logger.getLogger(Imprimir.class.getName()).log(Level.SEVERE, null, ex);
        }

        generalidades.addCell(new Phrase("NOMBRE DEL INGENIERO RESIDENTE:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getIngenieroResidente(), arial));
        generalidades.addCell(new Phrase("NOMBRE DEL INGENIERO AUXILIAR:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getIngenieroAuxiliar(), arial));
        generalidades.addCell(new Phrase("SECTOR ADMINISTRADO:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getSectorAdministrativo(), arial));
        generalidades.addCell(new Phrase("DIRECCION OFICINA:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getDireccionOficina(), arial));

        PdfPCell cellTel = new PdfPCell(new Phrase("TELEFONOS:", arial));
        cellTel.setColspan(2);
        cellTel.setHorizontalAlignment(Element.ALIGN_CENTER);
        generalidades.addCell(cellTel);
        generalidades.addCell(new Phrase("OFICINA:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getTelefono(), arial));
        generalidades.addCell(new Phrase("CELULARES: ", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getCelular(), arial));
        generalidades.addCell(new Phrase("FAX:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getFax(), arial));
        generalidades.addCell(new Phrase("CORREO ELECTRONICO:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getCorreo(), arial));

        generalidades.addCell(new Phrase("ADMINISTRADOR DE MANTENIMIENTO VIAL:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getCta(), arial));
        generalidades.addCell(new Phrase("LONGITUD ASIGNADA: ", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getLongitud(), arial));
        generalidades.addCell(new Phrase("PR INICIAL:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getPrInicial(), arial));
        generalidades.addCell(new Phrase("PR FINAL:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getPrFinal(), arial));
        generalidades.addCell(new Phrase("SECTOR: ", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getSector(), arial));
        generalidades.addCell(new Phrase("TIPO TERRENO:", arial));
        generalidades.addCell(new Phrase(this.proyectoDto.getProyecto().getTipoTerreno(), arial));
        generalidades.addCell(new Phrase("CANTIDAD DE ASOCIADOS:", arial));
        generalidades.addCell(new Phrase("", arial));

        return generalidades;
    }

    private PdfPTable actividades(Indicador indicador) {

        PdfPTable actividades = new PdfPTable(4);
        actividades.setWidthPercentage(100);
        actividades.getDefaultCell().setPadding(5);
        try {
            actividades.setWidths(new float[]{20, 30, 30, 30});
        } catch (DocumentException ex) {
            Logger.getLogger(Imprimir.class.getName()).log(Level.SEVERE, null, ex);
        }

        actividades.addCell(new Phrase("Tipo Actividad:", arial));
        actividades.addCell(new Phrase(indicador.getTipoIndicador(), arial));
        actividades.addCell(new Phrase("Tipo Informe: ", arial));
        actividades.addCell(new Phrase(indicador.getTipoInforme(), arial));

        actividades.addCell(new Phrase("Cantidad de obra ejecutada:", arial));
        if (Objects.nonNull(indicador.getCantidadEjecutada())) {
            actividades.addCell(new Phrase(indicador.getCantidadEjecutada().toString(), arial));
        } else {
            actividades.addCell(new Phrase(""));
        }
        actividades.addCell(new Phrase("Rendimiento esperado ", arial));
        actividades.addCell(new Phrase(indicador.getRendimiento() + " " + indicador.getTipoInforme() + "/hH", arial));

        PdfPCell cellObservacion = new PdfPCell(new Phrase("Observaciones: " + indicador.getObservacion(), arial));
        cellObservacion.setColspan(4);
        cellObservacion.setHorizontalAlignment(Element.ALIGN_CENTER);
        actividades.addCell(cellObservacion);
        return actividades;
    }

    private PdfPTable firmas() {

        PdfPTable firmas = new PdfPTable(4);
        firmas.setWidthPercentage(100);
        firmas.getDefaultCell().setPadding(5);
        try {
            firmas.setWidths(new float[]{25, 25, 25, 25});
        } catch (DocumentException ex) {
            Logger.getLogger(Imprimir.class.getName()).log(Level.SEVERE, null, ex);
        }
        PdfPCell dosColBlanco = new PdfPCell(new Phrase(" ", arial));
        dosColBlanco.setColspan(2);
        firmas.addCell(dosColBlanco);
        firmas.addCell(dosColBlanco);

        PdfPCell dirOficina = new PdfPCell(new Phrase(this.proyectoDto.getProyecto().getDireccionOficina(), arial));
        dirOficina.setColspan(2);
        dirOficina.setHorizontalAlignment(Element.ALIGN_CENTER);
        firmas.addCell(dirOficina);

        PdfPCell nombreResidente = new PdfPCell(new Phrase(this.proyectoDto.getProyecto().getIngenieroResidente(), arial));
        nombreResidente.setColspan(2);
        nombreResidente.setHorizontalAlignment(Element.ALIGN_CENTER);
        firmas.addCell(nombreResidente);

        firmas.addCell(new Phrase("correo electónico: " + this.proyectoDto.getProyecto().getCorreo(), arial));
        firmas.addCell(new Phrase("número de contacto: " + this.proyectoDto.getProyecto().getCelular(), arial));

        PdfPCell cargo = new PdfPCell(new Phrase("Ingeniero Residente", arial));
        cargo.setColspan(2);
        cargo.setHorizontalAlignment(Element.ALIGN_CENTER);
        firmas.addCell(cargo);
        return firmas;
    }

    private Paragraph titulo(String texto) {
        Paragraph titulo = new Paragraph();
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setFont(FontFactory.getFont("Verdana", 14, Font.BOLD, BaseColor.BLACK));
        titulo.add(texto);
        return titulo;
    }

    private Paragraph saltoLinea() {
        Paragraph saltolinea = new Paragraph();
        saltolinea.add("\n");
        return saltolinea;
    }

    private void abrirPdf() {
        File file = new File(this.nombre);

        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(Imprimir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void imprimir() {
        try {
            PdfWriter writer = null;
            Document documento = new Document(PageSize.A4, 40, 40, 10, 40);
            try {
                writer = PdfWriter.getInstance(documento, new FileOutputStream(this.nombre));
            } catch (FileNotFoundException | DocumentException ex) {
                ex.getMessage();
            }
            documento.addTitle("Impresion AMV");
            documento.open();

            documento.add(cabecera(writer));
            documento.add(saltoLinea());
            documento.add(titulo("GENERALIDADES"));
            documento.add(generalidades());
            documento.add(saltoLinea());
            documento.add(titulo("INFORME DE LAS ACTIVIDADES"));
            documento.add(saltoLinea());
            proyectoDto.getIndicadores().forEach(indicador -> {
                try {
                    documento.add(actividades(indicador));
                } catch (DocumentException ex) {
                    Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            documento.add(saltoLinea());
            documento.add(firmas());
            documento.close();
            writer.close();
            abrirPdf();
        } catch (DocumentException ex) {
            ex.printStackTrace();
            ex.getMessage();
        }
    }
}
