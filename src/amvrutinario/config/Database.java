/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amvrutinario.config;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Database {

    public Database() {
    }

    public Connection connect() {
        System.out.println("Database connect :: inicio");
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:./amv.db");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println("Database connect :: fin");
        return connection;
    }

    public ResultSet executeQuery(String query) {
        System.out.println("Database executeQuery :: inicio");
        ResultSet rs = null;
        try {
            Statement stmt = connect().createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Database executeQuery :: fin");
        return rs;
    }
}
