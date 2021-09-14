package com.example.Projekat;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public abstract class baza {
    private Connection veza;
    protected Statement statement;
    protected ResultSet rezultat;
    protected String sql;
    private String ime = "root";
    private String lozinka = "";
    private String url = "jdbc:mariadb://localhost/oglasimi";

    public baza() throws SQLException {
        veza = DriverManager.getConnection(url, ime, lozinka);
        statement = veza.createStatement();
    }

    public Connection dajVezu() {
        return veza;
    }

    public void zatvoriVezu() {
        if(veza != null) {
            try {
                statement.close();
                veza.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}