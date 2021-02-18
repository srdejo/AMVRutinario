/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amvrutinario.dao;

import amvrutinario.config.Database;
import amvrutinario.dto.Proyecto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class ProyectoDao {

    private static final String INSERT_PROYECTO = "INSERT INTO proyecto(ingeniero_residente,ingeniero_auxiliar,sector_administrativo"
            + ",direccion_oficina,telefono,celular,fax,correo,amv,cta,tipo_terreno,longitud,pr_inicial,pr_final,sector)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SELECT_SQL = "select * from proyecto";

    public Integer insert(Proyecto proyecto) {
        Database db = new Database();

        int numRowsInserted = 0;
        PreparedStatement ps = null;
        int generatedKey = 0;
        try {
            ps = db.connect().prepareStatement(INSERT_PROYECTO, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proyecto.getIngenieroResidente());
            ps.setString(2, proyecto.getIngenieroAuxiliar());
            ps.setString(3, proyecto.getSectorAdministrativo());
            ps.setString(4, proyecto.getDireccionOficina());
            ps.setString(5, proyecto.getTelefono());
            ps.setString(6, proyecto.getCelular());
            ps.setString(7, proyecto.getFax());
            ps.setString(8, proyecto.getCorreo());
            ps.setString(9, proyecto.getAmv());
            ps.setString(10, proyecto.getCta());
            ps.setString(11, proyecto.getTipoTerreno());
            ps.setString(12, proyecto.getLongitud());
            ps.setString(13, proyecto.getPrInicial());
            ps.setString(14, proyecto.getPrFinal());
            ps.setString(15, proyecto.getSector());
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
        System.out.println("Proyecto Dao :: inicio");
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
                proyectos.add(pr);
            }
            System.out.println("Proyecto Dao :: fin");
            return proyectos;
        } catch (SQLException e) {
            System.out.println("Proyecto Dao :: error");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
