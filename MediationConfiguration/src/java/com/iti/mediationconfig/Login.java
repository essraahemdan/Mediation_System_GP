/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.mediationconfig;

import database.AccessHandler;
import database.SessionManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anton
 */
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");
        HttpSession session= request.getSession(true);
        if (uname != null && !uname.isEmpty()) {
            if (pass != null && !pass.isEmpty()) {
                int validlogin = AccessHandler.checklogin(uname, pass);
                if(validlogin==1){
                System.out.println(validlogin);
                session.setAttribute("username", uname);
                session.setAttribute("password", pass);
                session.setAttribute("loginMessage", "");
                session.setAttribute("message", "");
                response.sendRedirect("home.jsp");
                
                }                    
                else{
                    System.out.println(validlogin); 
                    session.setAttribute("message", "");
                    session.setAttribute("loginMessage", "login insuccessful");
                    response.sendRedirect("loginAgain.jsp");
                }
            }
        }
    }
}
    

