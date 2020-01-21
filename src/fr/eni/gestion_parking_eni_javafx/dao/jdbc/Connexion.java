package fr.eni.gestion_parking_eni_javafx.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe Connexion
 */
public class Connexion
{
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:sqlserver://localhost;databasename=gestion_parking;user=sa;password=Pa$$w0rd");
    }

}
