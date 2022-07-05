package database;

import database.DatabaseConnector;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessHandler {

    public static int checklogin(String username, String password) {
        int validLogin = 0;

        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("select username , password from users where username = ? and password = ?");
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                    validLogin = 1;  
                
            }
        } catch (SQLException ex) {
                Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
            
      

    }
        return validLogin;
    }

    public static boolean checkregister(String name) throws SQLException {

        PreparedStatement stm = DatabaseConnector.con.prepareStatement("select * from users where username = ? ");
        stm.setString(1, name);

        ResultSet s = stm.executeQuery();

        if (s.next()) {
            stm.close();
            return false;
        } else {
            stm.close();
            return true;
        }
    }

    public static int register(Users user) throws SQLException {

        int error = 0;

        if (checkregister(user.getUname())) {
            try {

                PreparedStatement stm = DatabaseConnector.con.prepareStatement("insert into users (username , password) values  (?,?);");
                stm.setString(1, user.getUname());
                //stm.setString(2, user.email);
                stm.setString(2, user.getPassword());
                error = stm.executeUpdate();
                System.out.println(error);

            } catch (SQLException ex) {
                Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return error;
    }

    public static boolean checkserverdata(String name) throws SQLException {

        PreparedStatement stm = DatabaseConnector.con.prepareStatement("select * from server where name = ? ");
        stm.setString(1, name);
        //stm.setString(2,ip);
        ResultSet s = stm.executeQuery();

        if (s.next()) {
            stm.close();
            return false;
        } else {
            stm.close();
            return true;
        }
    }

    public static int addserverdetals(String servername, String description, String ip, int port, String protocol, String username, String password,
            String cdrpath,String cdrFormat) throws SQLException {
        int error = 0;

        if (checkserverdata(servername)) {
            try {

                PreparedStatement stm = DatabaseConnector.con.prepareStatement("insert into server (name,description,ip,port,protocol,username,password,cdrpath,cdrformat)values (?,?,?,?,?,?,?,?,?);");
                
                stm.setString(1, servername);
                stm.setString(2, description);
                stm.setString(3, ip);
                stm.setInt(4, port);
                stm.setString(5, protocol);
                stm.setString(6, username);
                stm.setString(7, password);
                stm.setString(8,cdrpath);
                stm.setString(9,cdrFormat);
               // stm.setInt(8, id);
                error = stm.executeUpdate();
                System.out.println("server added successfully");
                stm.close();

            } catch (SQLException ex) {
                System.out.println("server not added");
                Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return error;

    }

    public static int editserver(String servername, String description, String ip, int port, String protocol, String username, String password, String oldName)  {
        int error = 0;
        
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("update server set  description = ? , ip = ? , port = ? , protocol = ? , username = ? , password = ? where name = ?");
            //stm.setString(1, servername);
            stm.setString(1, description);
            stm.setString(2, ip);
            stm.setInt(3, port);
            stm.setString(4, protocol);
            stm.setString(5, username);
            stm.setString(6, password);
            stm.setString(7, oldName);
            error = stm.executeUpdate();
            System.out.println("updated");
            
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return error;
    }

    public static int deleteserver(String servername) throws SQLException {
        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement(" delete from server where name = ? ");
            stm.setString(1, servername);
            error = stm.executeUpdate();
            stm.close();
//            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    public static ArrayList<ServerData> listserver() throws SQLException {
        ArrayList<ServerData> myservers = new ArrayList<ServerData>();
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("select * from server ");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ServerData sd = new ServerData(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(1));
                myservers.add(sd);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return myservers;
    }

    public static int insert_into_cdr_location_info(String filepath, int server_id, String type) {
        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("insert into cdr_location_info(filepath,server_id,type) values (?,?,?)");
            //stm.setInt(1, cdr_li_id);
            stm.setString(1, filepath);
            stm.setInt(2, server_id);
            stm.setString(3, type);
            error = stm.executeUpdate();
            System.out.println("cdr_location_info added");
            stm.close();

        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return error;
    }

    public static int edit_cdr_location_infor(int cdr_li_id, String filepath, int server_id, String type, int oldcdrid) throws SQLException {
        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("update cdr_location_info set cdr_li_id = ? , filepath = ? , server_id = ? , type = ?  where cdr_li_id = ?");
            stm.setInt(1, cdr_li_id);
            stm.setString(2, filepath);
            stm.setInt(3, server_id);
            stm.setString(4, type);
            stm.setInt(5, oldcdrid);
            error = stm.executeUpdate();
            stm.close();
            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    public static int delete_cdr_location_info(int cdr_li_id) throws SQLException {
        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("delete from cdr_location_info  where cdr_li_id = ? ");
            stm.setInt(1, cdr_li_id);
            error = stm.executeUpdate();
            stm.close();
            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    public static int list_cdr_location_info() throws SQLException {
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("select * from cdr_location_info");
            stm.executeQuery();
            ResultSet rs = stm.executeQuery();
            if (rs.next() == false) {
                System.out.println("there is no record in database");
            } else {
                while (rs.next()) {
                    out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3) + rs.getInt(4));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return 0;
    }
    public static int getCDRLocationInfoIdFromServername(String servername) {

        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("select server_id from server where name=?");
            stm.setString(1,servername);
            ResultSet rs= stm.executeQuery();
            rs.next();
            int server_id=rs.getInt(1);
            stm = DatabaseConnector.con.prepareStatement("select cdr_li_id from cdr_location_info where server_id=?");
            stm.setInt(1,server_id);
            rs=stm.executeQuery();
            rs.next();
            return rs.getInt(1);
//            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return error;
    }


    public static int insert_into_rules(int source_cdr_li_id, int destination_cdr_li_id,int period_value, int period_unit) {

        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("insert into rules (source_cdr_li_id,destination_cdr_li_id)values (?,?)");
            stm.setInt(1, source_cdr_li_id);
            stm.setInt(2, destination_cdr_li_id);
            //stm.setInt(3, id);
          
            error = stm.executeUpdate();
            System.out.println("rules gg");
            stm.close();
//            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    public static int Edit_rules(int source_cdr_li_id, int destination_cdr_li_id, int period_value, int period_unit, int rid) {

        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("update rules set source_cdr_li_id = ? , destination_cdr_li_id = ? , period_value= ? ,period_unit = ?    where rules_id= ?");
            stm.setInt(1, source_cdr_li_id);
            stm.setInt(2, destination_cdr_li_id);
            stm.setInt(3, period_value);
            stm.setInt(4, period_unit);
            stm.setInt(5, rid);
            error = stm.executeUpdate();
            stm.close();
            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    public static int delete_rules(int rules_id) {
        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("delete from rules where rules_id = ? ");
            stm.setInt(1, rules_id);
            error = stm.executeUpdate();
            stm.close();
            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;
    }

    public static int list_rules() throws SQLException {
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("select * from rules ");
            stm.executeQuery();
            ResultSet rs = stm.executeQuery();
            if (rs.next() == false) {
                System.out.println("there is no record in database");
            } else {
                while (rs.next()) {
                    out.println(rs.getInt(1) + rs.getInt(2) + rs.getInt(3) + rs.getString(4) + rs.getString(5));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return 0;

    }

    public static int insert_into_cdr(int cdr_Id, int cdr_LI_Id, Boolean processed, String cdr_timestamp, String original_file_path, String converted_file_path) {

        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("insert into cdr values (?,?,?,?,?,?)");
            stm.setInt(1, cdr_Id);
            stm.setInt(2, cdr_LI_Id);
            stm.setBoolean(3, processed);
            stm.setString(4, cdr_timestamp);
            stm.setString(5, original_file_path);
            stm.setString(6, converted_file_path);
            error = stm.executeUpdate();
            System.out.println("cdr added");
            stm.close();
            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;

    }

    public static int edit_cdr(int cdr_Id, int cdr_LI_Id, Boolean processed, String cdr_timestamp, String original_file_path, String converted_file_path, int oldcdr_id) {
        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("update cdr set cdr_id = ? , cdr_li_id = ? , processed = ? , cdr_timestamp = ? , original_file_path = ? , converted_file_path = ?   where cdr_id = ?");
            stm.setInt(1, cdr_Id);
            stm.setInt(2, cdr_LI_Id);
            stm.setBoolean(3, processed);
            stm.setString(4, cdr_timestamp);
            stm.setString(5, original_file_path);
            stm.setString(6, converted_file_path);
            stm.setInt(7, oldcdr_id);
            error = stm.executeUpdate();
            System.out.println("cdr added");
            stm.close();
            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;

    }

    public static int delete_cdr(int cdr_id) {

        int error = 0;
        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("delete from cdr where cdr_id = ? ");
            stm.setInt(1, cdr_id);
            error = stm.executeUpdate();
            stm.close();
            DatabaseConnector.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return error;

    }

    public static int list_cdr() {

        try {
            PreparedStatement stm = DatabaseConnector.con.prepareStatement("select * from cdr ");
            stm.executeQuery();
            ResultSet rs = stm.executeQuery();
            if (rs.next() == false) {
                System.out.println("there is no record in database");
            } else {
                while (rs.next()) {
                    out.println(rs.getInt(1) + rs.getInt(2) + rs.getInt(3) + rs.getString(4) + rs.getString(5) + rs.getString(6));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessHandler.class.getName()).log(Level.SEVERE, null, ex);

        }
        return 0;

    }
}
