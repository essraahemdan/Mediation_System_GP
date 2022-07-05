/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.mediationconfig;

import database.AccessHandler;
import database.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anton
 */
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("usernamesignup");
        String pass = request.getParameter("passwordsignup");
        String checkpassword = request.getParameter("passwordsignup_confirm");
        HttpSession session= request.getSession(true);
        int registerStatus = -1;
        if (uname != null && !uname.isEmpty()) {
            if (pass != null && !pass.isEmpty()) {
                if (pass.equals(checkpassword)) {
                    try {
                        Users user = new Users();
                        user.setUname(uname);
                        user.setPassword(pass);
                        registerStatus = AccessHandler.register(user);
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    registerStatus=-2;
                    session.setAttribute("message", "");
                    session.setAttribute("registerMessage", "passwords don't match");
                    response.sendRedirect("loginAgain.jsp#toregister");
                }
            }
        }
        
    if (registerStatus==1){
        session.setAttribute("username", uname);
        session.setAttribute("password", pass);
        session.setAttribute("message", "");
        session.setAttribute("registerMessage", "Registration successful");
        response.sendRedirect("loginAgain.jsp");
    }
    else if (registerStatus==0){
        session.setAttribute("message", "");
        session.setAttribute("registerMessage", "this username is already taken");
        response.sendRedirect("loginAgain.jsp#toregister");
    }
    else if (registerStatus ==-1){
        session.setAttribute("message", "");
        session.setAttribute("registerMessage", "please enter valid data");
        response.sendRedirect("loginAgain.jsp#toregister");
    }
    }


}
