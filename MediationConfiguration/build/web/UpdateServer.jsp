
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
    </head>
    <body>

        <div class="container">
            <!-- Codrops top bar -->
            <div class="codrops-top">

                <span class="right">

                </span>
                <div class="clr"></div>
            </div>
            <header>

            </header>
            <section>				
                <div id="container_demo" >

                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <form  action="updateServer" autocomplete="on" method="GET"> 
                                
                                <input type="hidden" value="<%out.print(request.getParameter("id"));%>"/>
                                 <p> 
                                <h1 style="font-size: 24px ">Server Data</h1>
                                <h2 style="font-color:red"><%
                                    session = request.getSession(false);
                                    System.out.println(session.getAttribute("username"));
                                    if (session == null)

                                        response.sendRedirect("index.html");%>
                                </h2>
                                    <label for="servername" class="uname"  > server_name </label>
                                    <input id="servername" name="servername" required="required" type="text" />
                            </p>
                                    <!--                                <p> 
                                                                        <label for="description" class="uname"  > description </label>
                                                                        
                                                                        <br>
                                                                        <select name="description">
                                                                            <option value="upstream_server"> upstream_server </option>
                                                                            <option value="downstream_server">downstream_server</option>
                                                                        </select>
                                                                    </p>-->

                                    <p> 
                                        <!--                                    <input type="text" minlength="7" maxlength="15" size="15" pattern="^((\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.){3}(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$">
                                        --> <label for="IP" class="uname"  > IP </label>
                                        <input id="ip" name="ip" required="required" type="text"/>
                                    </p>
                                    <p> 
                                        <label for="Port" class="uname"  > Port </label>
                                        <input id="port" name="port" required="required" type="number" />
                                    </p>

                                    <p> 
                                        <label for="Protocol" class="uname"  > Protocol</label>
                                        <br>
                                        <select name="Protocol" >
                                            <option>FTP</option>
                                            <option>SCP</option>
                                        </select>
                                        <br>  

                                    </p>
                                    <p> 
                                        <label for="username" class="uname" data-icon="u" >username </label>
                                        <input id="username" name="username" required="required" type="text" placeholder="myusername or mymail@mail.com"/>
                                    </p>
                                    <p> 
                                        <label for="password" class="youpasswd" data-icon="p">password </label>
                                        <input id="password" name="password" required="required" type="password"  /> 
                                    </p>

                                    <p class="login button"> 
                                        <input type="submit" value="Update" /> 
                                    </p>

                            </form>
                        </div>
                    </div>
                </div>  
            </section>
        </div>

        <!--        <%
            /*session=request.getSession(false);
                    System.out.println(session.getAttribute("username"));
                    if(session==null)
                        response.sendRedirect("index.html");*/
 /*String servername = request.getParameter("servername");
                    String description = request.getParameter("description");
                    String ip = request.getParameter("ip");
                    String port = request.getParameter("port");
                    String protocol = request.getParameter("Protocol");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                        if (servername != null && !servername.isEmpty()) {
                            if (description != null && !description.isEmpty()) {
                                if (ip != null && !ip.isEmpty()) {
                                    if (port != null && !port.isEmpty()) {
                                        if (protocol != null && !protocol.isEmpty()) {
                                            if (username != null && !username.isEmpty()) {
                                                if (password != null && !password.isEmpty()) {
                                                    int error = AccessHandler.addserverdetals(servername, description, ip, Integer.parseInt(port), protocol, username, password);
                                                    if (error == 0) {
                                                        response.sendRedirect("add_server_error.jsp");

                                                    }
                                                    else 
                                                    {
                                                        response.sendRedirect("listservers.jsp");
                                                    }
                                            
                                        

                                            }
                                        }

                                    }

                                }
                            }
                        }
                    }

             */
        %>-->
    </body>
</html>