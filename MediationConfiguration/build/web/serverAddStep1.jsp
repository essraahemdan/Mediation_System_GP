
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
            </header>
        <br><br>

        <div class="container">
            <!-- Codrops top bar -->
            <div class="codrops-top">

                <span class="right">

                </span>
                <div class="clr"></div>
            </div>
            
            <section>				
                <div id="container_demo" >

                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <form  action="serverAddStep2.jsp" autocomplete="on"> 
                                <h1>Step 1:2</h1>

                                <p> 
                                <h1 style="font-size: 24px ">Server Data</h1>
                                <h2 style="color:red"><%
                                    session = request.getSession(false);
                                    System.out.println(session.getAttribute("username"));
                                    if (session == null) {
                                        response.sendRedirect("index.html");
                                    }
                                    if (session.getAttribute("message") != null)
                                        out.print(session.getAttribute("message"));%><h2/>
                                    <label for="servername" class="uname"  > server_name </label>
                                    <input id="servername" name="servername" required="required" type="text" />
                                    </p>
                                    <p> 
                                        <label for="description" class="uname"  > description </label>

                                        <br>
                                        <select name="description">
                                            <option value="upstream_server"> upstream_server </option>
                                            <option value="downstream_server">downstream_server</option>
                                        </select>
                                    </p>

                                    <p> 
                                        <label for="IP" class="uname"  > IP </label>
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
                                        <input type="submit" value="Next" /> 
                                    </p>

                            </form>
                        </div>
                    </div>
                </div>  
            </section>
        </div>


    </body>
</html>