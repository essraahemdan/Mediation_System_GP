package com.iti.mediationconfig;

import database.AccessHandler;
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
import javax.servlet.http.HttpSession;

public class updateServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("username") != null) {
            String servername = request.getParameter("servername");
            String description = request.getParameter("description");
            String ip = request.getParameter("ip");
            String port = request.getParameter("port");
            String protocol = request.getParameter("Protocol");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String oldName = request.getParameter("oldName");
            try {
                PreparedStatement stmt = DatabaseConnector.con.prepareStatement("select * from server where name=?");
                stmt.setString(1, servername);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    stmt = DatabaseConnector.con.prepareStatement("select * from server where ip = ?");
                    stmt.setString(1, ip);
                    rs = stmt.executeQuery();
                    if (!rs.next()) {
                        System.out.println("gg now can update");
                        int error = AccessHandler.editserver(servername, description, ip, Integer.parseInt(port), protocol, username, password, oldName);
                        if (error == 0) {

                            response.sendRedirect("UpdateServer.jsp");

                        } else {
                            response.sendRedirect("listServers.jsp");

                        }

                    } else {
                        session.setAttribute("message", "another server is using the same ip");
                        response.sendRedirect("UpdateServer.jsp");
                    }
                } else {
                    session.setAttribute("message", "no server found with such name");
                    response.sendRedirect("UpdateServer.jsp");
                }

            } catch (SQLException ex) {
                Logger.getLogger(updateServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       
    }

}
