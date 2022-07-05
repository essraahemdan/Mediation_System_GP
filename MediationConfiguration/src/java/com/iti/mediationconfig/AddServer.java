package com.iti.mediationconfig;

import database.AccessHandler;
import database.DatabaseConnector;
import static database.DatabaseConnector.con;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddServer extends HttpServlet {

    private static final List<String> cdrStruct = new ArrayList();
    private static final List<String> cdrStructType = new ArrayList();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //System.out.println("ggmyboy");
            cdrStruct.add("Entry_No");
            cdrStruct.add("Calling Number");
            cdrStruct.add("Called Number");
            cdrStruct.add("Call Duration");
            cdrStruct.add("Call Type");
            cdrStruct.add("Generation Time");
            cdrStructType.add("Integer");
            cdrStructType.add("PhoneNumber");
            cdrStructType.add("PhoneNumber");
            cdrStructType.add("Duration");
            cdrStructType.add("State");
            cdrStructType.add("TimeStamp");
            
            HttpSession session = request.getSession(false);
            if (session.getAttribute("username") == null) {
                response.sendRedirect("index.html");
            }
            String servername = (String) session.getAttribute("servername");
            String ip = (String) session.getAttribute("ip");
            String description = (String) session.getAttribute("description");
            String port = (String) session.getAttribute("port");
            String protocol = (String) session.getAttribute("protocol");
            String serverusername = (String) session.getAttribute("serverusername");
            String serverpassword = (String) session.getAttribute("serverpassword");
            String filepath = request.getParameter("path");
            String type = request.getParameter("type");
            if (filepath != null && !filepath.isEmpty()) {
                if (type != null && !type.isEmpty()) {
                    session.setAttribute("cdrpath", filepath);
                    session.setAttribute("cdrtype", type);
                    session.setAttribute("message", "");
                }
            }
            String cdrPath = (String) session.getAttribute("cdrpath");
            String cdrFormat = (String) session.getAttribute("cdrtype");

            int error = AccessHandler.addserverdetals(servername, description, ip, Integer.parseInt(port), protocol, serverusername, serverpassword, cdrPath, cdrFormat); //done
            System.out.println(error + "error");
            response.sendRedirect("listServers.jsp");

//                    PreparedStatement stmt = DatabaseConnector.con.prepareStatement("select server_id from server where name=?"); //now we have added server id and added servername and rule servername
//                    stmt.setString(1, servername);
//                    ResultSet rs = stmt.executeQuery();
//                    rs.next();
//                    System.out.println(rs.getInt(1) + "gg");
//                    int server_id = rs.getInt(1);
//
//                    int max_cdrli_id = 0;
//                    error = AccessHandler.insert_into_cdr_location_info(cdrpath, server_id, cdrtype);
//                    stmt = DatabaseConnector.con.prepareStatement("select max(cdr_li_id) from cdr_location_info;");
//                    rs = stmt.executeQuery();
//                    rs.next();
//                    max_cdrli_id = rs.getInt(1);
//                    /*stmt=DatabaseConnector.con.prepareStatement("select cdr_li_id from cdr_location_info where filepath=? and server_id=? and type=?");
//            stmt.setString(1,);
//            stmt.setString();
//            stmt.setString();
//            
//            rs=stmt.executeQuery();
//            rs.next();
//            int cdr_li_id=rs.getInt(1);*///id of location on new server
//
//                    /*PreparedStatement stmt = DatabaseConnector.con.prepareStatement("");
//            stmt.setString(1,servername);
//            ResultSet rs =stmt.executeQuery();
//            rs.next()
//            int server_id=rs.getInt(1);*/
// /*stmt = DatabaseConnector.con.prepareStatement("select max(rules_id) from rules;");
//            rs=stmt.executeQuery();
//            rs.next();
//            int max_rules_id=rs.getInt(1);*/
//                    System.out.println("before if condition");
//                    System.out.println(description);
//                    /* if (description.equals("upstream_server")){
////            {stmt=DatabaseConnector.con.prepareStatement("select max(server_id) from server;");
////            rs=stmt.executeQuery();
////            rs.next();
////            server_id=rs.getInt(1);
//            stmt=DatabaseConnector.con.prepareStatement("select cdr_struct , cdr_struct_type from cdr_structure limit 1;");
//            rs=stmt.executeQuery();
//            rs.next();
//            List<String> cdr_struct = (ArrayList<String>)rs.getArray(1);
//            List<String> cdr_struct_type = (ArrayList<String>)rs.getArray(2);
//            
//            setCDRStructure(cdr_struct,cdr_struct_type);
//
//            
//            }*/
//                    int max_rules_id = 0;
//                    int ruleid = AccessHandler.getCDRLocationInfoIdFromServername(ruleservername);
////            stmt = DatabaseConnector.con.prepareStatement("select server_id from server where name=?"); //now we have added server id and added servername and rule servername
////            stmt.setString(1, ruleservername);
////            rs = stmt.executeQuery();
////            rs.next();
////            int server2_id = rs.getInt(1);
//
//                    if (description.equals("upstream_server")) {
//                        System.out.println("inside if condition");
//                        error = AccessHandler.insert_into_rules(max_cdrli_id, ruleid, 5, 5);
////                setCDRStructure(server_id);
//                        response.sendRedirect("listServers.jsp");
//                    } else if (description.equals("downstream_server")) {
//                        System.out.println("Source: " + (max_rules_id + 1) + "Dest: " + ruleid);
//                        error = AccessHandler.insert_into_rules(ruleid, max_cdrli_id, 5, 5);
//
//                        response.sendRedirect("listServers.jsp");
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(AddServer.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//
////    public static int setCDRStructure(int serverId) {
////        //Connection con;
////        System.out.println("into set cdr");
////        PreparedStatement pst;
////        int insertionStatus = 0;
////        String[] cdrStructArr = new String[cdrStruct.size()];
////        String[] cdrStructTypeArr = new String[cdrStructType.size()];
////        //DatabaseConnector.initiateDBConnection();
////        //con = DatabaseConnector.getDBConnection();
////        for (int i = 0; i < cdrStruct.size(); i++) {
////            cdrStructArr[i] = cdrStruct.get(i);
////        }
////        for (int i = 0; i < cdrStruct.size(); i++) {
////            cdrStructTypeArr[i] = cdrStructType.get(i);
////        }
////        try {
////            pst = DatabaseConnector.con.prepareStatement("insert into CDR_Structure (server_id) values (?)");
////            pst.setInt(1, serverId);
////
////            insertionStatus = pst.executeUpdate();
////
////            con.close();
////        } catch (SQLException ex) {
////            ex.printStackTrace();
////        }
////
////        return insertionStatus;
////
////    }
        } catch (SQLException ex) {
            Logger.getLogger(AddServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}