
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
        <div class="container">
            <!--             Codrops top bar -->
            <div class="codrops-top">

                <span class="right">

                </span>
                <div class="clr"></div>
            </div>
            <header class="topnav">
                <a class="active" href="http://localhost:8080/MediationConfiguration/home.jsp">Home</a>
                <a href="http://localhost:8080/MediationConfiguration/serverCRUD.jsp">Process</a>
                
            </header>
            <section>				
                <div id="container_demo" >

                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div style="background-color: transparent; margin-top: 5cm; text-align: center;" id="login" class="animate form">
                            <br>
                            <br>

                            <p class="login button">
                                <a href="serverCRUD.jsp">
                                    <input style="width: 200px" type="submit" value="Server Configuration" /> 
                                </a>				</p>
                            <br>
                            <br>


                        </div>
                    </div>
                </div>  
            </section>
        </div>
    </body>
</html>