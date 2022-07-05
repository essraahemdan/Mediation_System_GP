package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Essraa
 */
public class DatabaseCon {
    
    private Connection connection = null;
    private PreparedStatement preStm = null;
    ResultSet rs = null;
    private static DatabaseCon databaseInstance = new DatabaseCon();


    private static final String url = "jdbc:postgresql://localhost:5432/mediation_db";
    private static final String user = "postgres";
    private final static String password = "root";    

   public void connect() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is made successfully");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("DatabaseConnection.DatabaseConnection.connect()error");
        }

    }
    public static DatabaseCon getDatabaseInstance() {
        return databaseInstance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
