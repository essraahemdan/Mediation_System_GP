/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.mediationconfig;

import database.DatabaseConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anton
 */
public class DeleteServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int server_id= Integer.parseInt(request.getParameter("id"));
            System.out.println(server_id + "this is server id");
            PreparedStatement stm =DatabaseConnector.con.prepareStatement("delete from server where server_id =?;");
            stm.setInt(1,server_id);
            stm.executeUpdate();
            response.sendRedirect("listServers.jsp");
            
        } catch (SQLException ex) {
            Logger.getLogger(DeleteServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("not deleted");
        }
        
    }
}
