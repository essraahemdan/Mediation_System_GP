/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Essraa
 */
public class DatabaseHandler {

    private PreparedStatement pst = null;
    ResultSet result = null;
    DatabaseCon db = DatabaseCon.getDatabaseInstance();

    public ServerData getServerData(String name) {
    ServerData sd = new ServerData();

        try {
            pst = db.getConnection().prepareStatement("select ip,port,username,password from server where name=?");
            pst.setString(1, name);
            result = pst.executeQuery();
            while (result.next()) {
                sd.setServerIP(result.getString("ip"));
                sd.setServerPort(result.getInt("port"));
                sd.setServerUserName(result.getString("username"));
                sd.setServerPassword(result.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sd;
    }
public String getCDRPath(String name){
String path = null ;
 try {
            pst = db.getConnection().prepareStatement("select cdrpath from server where name=?");
            pst.setString(1, name);
            result = pst.executeQuery();
            while (result.next()) {
                path = result.getString("cdrpath");
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return path;
}
}
