<%@page import="database.SessionManager"%>
<%@page import="database.ServerData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="database.DatabaseConnector"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="database.AccessHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js"> 
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Login and Registration Form with HTML5 and CSS3" />
        <meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/animation.css" />
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
    <body style="background-color:#ce42f5;">
        <header class="topnav">
                <a class="active" href="http://localhost:8080/MediationConfiguration/home.jsp">Home</a>
                <a href="http://localhost:8080/MediationConfiguration/serverCRUD.jsp">Process</a>
                <%

                    if (session != null) {
                        String uname = session.getAttribute("username").toString();
                        String pass = session.getAttribute("password").toString();

                        String servername = request.getParameter("servername");
                        String description = request.getParameter("description");
                        String ip = request.getParameter("ip");
                        String port = request.getParameter("port");
                        String protocol = request.getParameter("Protocol");
                        String serverusername = request.getParameter("username");
                        String serverpassword = request.getParameter("password");

                        if (servername != null && !servername.isEmpty()) {
                            if (description != null && !description.isEmpty()) {
                                if (ip != null && !ip.isEmpty() && SessionManager.validate(ip)) {
                                    if (port != null && !port.isEmpty()) {
                                        if (protocol != null && !protocol.isEmpty()) {
                                            if (serverusername != null && !serverusername.isEmpty()) {
                                                if (serverpassword != null && !serverpassword.isEmpty()) {
                                                    PreparedStatement stmt = DatabaseConnector.con.prepareStatement("select * from server where name=? or ip= ?;");
                                                    stmt.setString(1, servername);
                                                    stmt.setString(2, ip);
                                                    ResultSet s = stmt.executeQuery();
                                                    if (!(s.next())) {
                                                        session.setAttribute("servername", servername);
                                                        session.setAttribute("ip", ip);
                                                        session.setAttribute("description", description);
                                                        session.setAttribute("port", port);
                                                        session.setAttribute("protocol", protocol);
                                                        session.setAttribute("serverusername", serverusername);
                                                        session.setAttribute("serverpassword", serverpassword);
                                                        session.setAttribute("message", "");
                                                        System.out.println(session.getAttribute("message"));

                                                        //                                                ServerData serverData=new ServerData( servername, description,ip , port,protocol , serverusername,serverpassword );
                                                    } else {
                                                        session.setAttribute("message", "A server with the same ip or name already exists");
                                                        response.sendRedirect("serverAddStep1.jsp");
                                                    }

                                                }
                                            }
                                        }
                                    }
                                } else {
                                    session.setAttribute("message", "invalid ip");
                                    response.sendRedirect("serverAddStep1.jsp");
                                }
                            }
                        }
                    } else {
                        session.setAttribute("message", "");
                        response.sendRedirect("index.html");
                    }


                %>
            </header>
            <br><br> 
        <div class="container">

            <div class="codrops-top">

                <span class="right"></span>

                <div class="clr"></div>
            </div>
            
            <section>				
                <div id="container_demo" >

                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <form  action="AddServer" autocomplete="on" method="Post"> 
                                <h1>Step 2:2</h1>
                                <p> 
                                <h1 style="font-size: 24px ">CDRs location</h1>
                                <%  session = request.getSession(false);
                                    System.out.println(session.getAttribute("username"));
                                    if (session == null) {
                                        response.sendRedirect("index.html");
                                    }
                                    if (session.getAttribute("message") != "")

                                        out.print(session.getAttribute("message"));%>
                                </p>
                                <p> 
                                    <label for="filepath" class="uname"  > filepath </label>
                                    <input id="filepath" name="path" required="required" type="text" />
                                </p>

                                <p> 
                                    <label for="type" class="uname"  >type</label>
                                    <input id="type" name="type" required="required" type="text" />
                                </p>


                                <p class="login button"> 
                                    <input type="submit" value="Finsh" /> 
                                </p>                               
                            </form>
                        </div>				
                    </div>
                </div>  
            </section>
        </div>

    </body>
</html>
