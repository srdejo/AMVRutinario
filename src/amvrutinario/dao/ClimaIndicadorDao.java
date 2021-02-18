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
public class ClimaIndicadorDao {

    private static final String INSERT_CLIMA = "INSERT INTO clima_indicador(clima, id_indicador)"
            + " VALUES(?, ?)";

    private static final String SELECT_SQL = "select * from clima_indicador";

    public Integer insert(String clima, int indicadorId) {
        Database db = new Database();

        int numRowsInserted = 0;
        PreparedStatement ps = null;
        int generatedKey = 0;
        try {
            ps = db.connect().prepareStatement(INSERT_CLIMA, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clima);
            ps.setInt(2, indicadorId);
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
