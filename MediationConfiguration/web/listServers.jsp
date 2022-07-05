<%@page import="java.util.List"%>
<%@page import="database.ServerData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.AccessHandler"%>
<%@page import="com.sun.java.accessibility.AccessBridge"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<!DOCTYPE html>-->
<html lang="en" class="no-js">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Login and Registration Form with HTML5 and CSS3" />
        <meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico">
        <link rel="stylesheet" type="text/css" href="css/home.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/animation.css" />
        <link rel="stylesheet" type="text/css" href="css/Display.css" />
        <link rel="stylesheet" type="text/css" href="css/Display2.css" />
        <link rel="stylesheet" type="text/css" href="css/CRUD.css" />
        <style>


            .topnav {
                overflow: hidden;
                background-color: #ffff;
            }

            .topnav a {
                float: left;
                color: black;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 17px;
            }

            .topnav a:hover {
                background-color: #ddd;
                color: black;
            }

            .topnav a.active {
                background-color: #04AA6D;
                color: white;
            }
        </style>
    </head>
    <body>
        <header class="topnav">
            <a class="active" href="http://localhost:8080/MediationConfiguration/home.jsp">Home</a>
            <a href="http://localhost:8080/MediationConfiguration/serverCRUD.jsp">Process</a>

        </header>
        <table width="300px" border="2px" cellpadding = "5px">
            <caption><h1 >Servers information</h2></caption>
            <thead >
                <tr >
                    <th width="25%">name</th>
                    <th>description</th>
                    <th>ip</th>
                    <th>port</th>
                    <th>Protocol</th>
                    <th>username</th>
                    <th>password</th>
                    <th>id</th>
                    <th>action</th>
                </tr>
            </thead>
            <tbody >




                <%

                    List<ServerData> listofservers = new ArrayList();
                    listofservers = AccessHandler.listserver();
                    System.out.println(listofservers.size());
                    for (ServerData serverdata : listofservers) {
                        out.print(" <tr style=\"font-color:black\">"
                                + "<td name=\"sn\" style=\"font-color:black\">" + serverdata.name + "</td>"
                                + "<td name=\"des\" style=\"font-color:black\">" + serverdata.description + "</td>"
                                + "<td name=\"ip\">" + serverdata.ip + "</td>"
                                + "<td name=\"port\" style=\"font-color:black\">" + serverdata.port + "</td>"
                                + "<td name=\"pro\" style=\"font-color:black\">" + serverdata.protocol + "</td>"
                                + "<td name=\"un\" style=\"font-color:black\">" + serverdata.username + "</td>"
                                + "<td name=\"pass\" style=\"font-color:black\">" + serverdata.password + "</td>"
                                + "<td name=\"sid\" style=\"font-color:black\">" + serverdata.server_id + "</td>"
                                + "<td name=\"sid\" style=\"font-color:black\"><input type=\"hidden\" value=\"" + serverdata.server_id + "\"><a href=\"DeleteServer?id=" + serverdata.server_id + "\"><button >delete</button></a><a href=\"UpdateServer.jsp?id=" + serverdata.server_id + "\"><button >update</button></a></td>"
                                + "</tr> ");
                        System.out.println(serverdata.name + serverdata.description + serverdata.ip + serverdata.port + serverdata.protocol + serverdata.username + serverdata.password + serverdata.server_id);
                    }


                %>
            </tbody>
        </table>
    </body>
</html>