/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amvrutinario.dao;

import amvrutinario.config.Database;
import amvrutinario.dto.Indicador;
import amvrutinario.dto.Proyecto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Daniel
 */
public class IndicadorDao {

    private static final String INSERT_INDICADOR = "INSERT INTO indicador(tipo_informe, pr_inicio, pr_fin, horas_trabajadas, obreros,"
            + "total_horas, equipo, cantidad_ejecutada, rendimiento, observacion, tipo_indicador, proyecto_id)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_SQL = "select * from indicador";

    public Integer insert(Indicador indicador, int proyectoId) {
        Database db = new Database();

        int numRowsInserted = 0;
        PreparedStatement ps = null;
        int generatedKey = 0;
        try {
            ps = db.connect().prepareStatement(INSERT_INDICADOR, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, indicador.getTipoInforme());
            ps.setString(2, indicador.getPrInicio());
            ps.setString(3, indicador.getPrFin());
            if (Objects.nonNull(indicador.getHorasTrabajadas())) {
                ps.setInt(4, indicador.getHorasTrabajadas());
            }
            if (Objects.nonNull(indicador.getObreros())) {
                ps.setInt(5, indicador.getObreros());
            }
            ps.setString(6, indicador.getTotalHoras());
            ps.setString(7, indicador.getEquipo());
            if (Objects.nonNull(indicador.getCantidadEjecutada())) {
                ps.setInt(8, indicador.getCantidadEjecutada());
            }
            ps.setString(9, indicador.getRendimiento());
            ps.setString(10, indicador.getObservacion());
            ps.setString(11, indicador.getTipoIndicador());
            ps.setInt(12, proyectoId);
            numRowsInserted = ps.executeUpdate();
            if (numRowsInserted > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ps);
        }
        return generatedKey;
    }

    public List<Proyecto> getAll() {

        Database db = new Database();

        try {
            List<Proyecto> proyectos = new ArrayList<>();
            ResultSet rs = db.executeQuery(SELECT_SQL);
            while (rs.next()) {
                Proyecto pr = new Proyecto();
                pr.setAmv(rs.getString("amv"));
                pr.setCta(rs.getString("cta"));
                pr.setPrInicial(rs.getString("pr_inicial"));
                pr.setPrFinal(rs.getString("pr_final"));
                pr.setId(rs.getInt("id"));
                /*
                id, ingeniero_residente,ingeniero_auxiliar,sector_administrativo"
            + ",direccion_oficina,telefono,celular,fax,correo,amv,cta,tipo_terreno,longitud,pr_inicial,pr_final,sector
                 */
                proyectos.add(pr);
            }
            return proyectos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
