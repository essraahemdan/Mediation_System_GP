
<%@page import="database.DatabaseConnector"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="database.AccessHandler"%>
<!DOCTYPE html>
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
    <body style="background-color:#ce42f5;">
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
                            <form  action="AddServer" autocomplete="on" method="Post"> 
                                <h1>Step 3:3</h1>
                                <p> <h1 style="font-size:24px">Add rule</h1>
                                <%
                                    session = request.getSession(false);
                                    System.out.println(session.getAttribute("username"));
                                    if (session == null) {
                                        response.sendRedirect("index.html");
                                    }
                                    out.print(session.getAttribute("message"));%>

                                <%System.out.println(session.getAttribute("description"));
                                    if (session.getAttribute("description").equals("upstream_server")) {
                                        System.out.println("gg");
                                        out.print("<p> <label for=\"rule_server_name\" class=\"uname\"  >Destination server name</label><input id=\"rule_server_name\" name=\"rule_server_name\" required=\"required\" type=\"text\" </p>");
                                    } else if (session.getAttribute("description").equals("downstream_server")) {
                                        out.print("<p> <label for=\"rule_server_name\" class=\"uname\"  >Source server name<label><input id=\"rule_server_name\" name=\"rule_server_name\" required=\"required\" type=\"text\" </p>");
                                    }
                                %>

                                <p> 
                                    <label for="period_value" class="uname"  > period_value</label>
                                    <input id="period_value" name="period_value" required="required" type="number" />
                                </p>
                                <p> 
                                    <label for="period_unit" class="uname"  > period_unit </label>
                                    <input id="period_unit" name="period_unit" required="required" type="number" />
                                </p>


                                <p class="login button"> 
                                    <input type="submit" value="Finish" /> 
                                </p>

                            </form>
                        </div>
                        <%  session = request.getSession(false);
                            if (session != null) {
                                
                                String filepath = request.getParameter("path");
                                String type = request.getParameter("type");
                                if (filepath != null && !filepath.isEmpty()) {
                                    if (type != null && !type.isEmpty()) {
                                        session.setAttribute("cdrpath", filepath);
                                        session.setAttribute("cdrtype", type);
                                        session.setAttribute("message", "");

                                        //AccessHandler.insert_into_cdr_location_info(Integer.parseInt(cdrid), filepath, Integer.parseInt(serverid), type);
                                    }
                                }

                            } else {
                                session.setAttribute("message", "invalid data");
                                response.sendRedirect("index.html");
                            }

                            /*String src_cdr = request.getParameter("source_cdr_li_id");
                                    String des_cdr = request.getParameter("destination_cdr_li_id");
                                    String period_value = request.getParameter("period_value");
                                    String period_unit = request.getParameter("period_unit");
                                    if (src_cdr != null && !src_cdr.isEmpty()) {
                                        {
                                            if (des_cdr != null && !des_cdr.isEmpty()) {

                                                if (period_value != null && !period_value.isEmpty()) {

                                                    if (period_unit != null && !period_unit.isEmpty()) {
                                
                                                        //AccessHandler.insert_into_rules(Integer.parseInt(src_cdr), Integer.parseInt(des_cdr), Integer.parseInt(period_value), Integer.parseInt(period_unit));
                                                    }
                                                }

                                            }

                                        }
                                    }*/

                        %>



                    </div>
                </div>  
            </section>
        </div>


    </body>
</html>

