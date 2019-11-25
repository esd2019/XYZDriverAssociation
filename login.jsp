
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account | Enterprise Systems Development</title>
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/font-awesome.min.css" rel="stylesheet">
        <link href="resources/css/prettyPhoto.css" rel="stylesheet">
        <link href="resources/css/price-range.css" rel="stylesheet">
        <link href="resources/css/animate.css" rel="stylesheet">
        <link href="resources/css/main.css" rel="stylesheet">
        <link href="resources/css/responsive.css" rel="stylesheet">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
   


        <script src="resources/js/html5shiv.js"></script>
        <script src="resources/js/validateAccount.js"></script>

        <script src="resources/js/jquery.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/jquery.scrollUp.min.js"></script>
        <script src="resources/js/price-range.js"></script>
        <script src="resources/js/jquery.prettyPhoto.js"></script>
        <script src="resources/js/main.js"></script>
        
        <script>
            function focus() {
                document.getElementById("email").focus;
            }
        </script>
    </head>
    <body onload="focus()">
        <!--HEADER-->
        <%
            String email_err = "", fullname_err = "", address_err = "", email_login_err = "", login_err = "";

            if (request.getAttribute("email_err") != null) {
                email_err = (String) request.getAttribute("email_err");
            }
            if (request.getAttribute("fullname_err") != null) {
                fullname_err = (String) request.getAttribute("fullname_err");
            }

            if (request.getAttribute("address_err") != null) {
                address_err = (String) request.getAttribute("address_err");
            }

            String email = "", fullname = "", address = "";

            if (request.getAttribute("email") != null) {
                email = (String) request.getAttribute("email");
            }
            if (request.getAttribute("fullname") != null) {
                fullname = (String) request.getAttribute("fullname");
            }

            if (request.getAttribute("address") != null) {
                address = (String) request.getAttribute("address");
            }

            if (request.getAttribute("email_login_err") != null) {
                email_login_err = (String) request.getAttribute("email_login_err");
            }
            if (request.getAttribute("login_err") != null) {
                login_err = (String) request.getAttribute("login_err");
            }

        %>



        <!--MAIN CONTENT + CATEGORY MENU-->
        <section id="form"><!--form-->

            <div class="container">

                <div class="row">
                    <div class="col-sm-4 col-sm-offset-1">
                        <div class="login-form"><!--login form-->
                            <h2>Login to your account</h2>
                            <form action="AccountController" method="post">
                                <p style="color: red"><%= email_login_err%></p>
                                <p id="err8" style="color: red"></p>
                                <input type="text" placeholder="Email Address" name ="email" id="email" oninput="checkEmail_login()" />
                                <p id="err9" style="color: red"></p>
                                <input type="password" placeholder="Password" name="password" id="password" oninput="checkPassword_login()" />


                                <p style="color: red"><%= login_err%></p>

                                <span>
                                    <input type="checkbox" class="checkbox" name="remember"> 
                                    Keep me signed in
                                </span>
                                <input type="hidden" name="action" value="login" />
                                <%if (request.getParameter("checkout") != null) {%><input type="hidden" name="checkout" value="checkout" /><%}%>
                                <button type="submit" class="btn btn-default">Login</button>
                                <div>
                                    <a href="homepage.jsp" class="w3-button w3-left w3-light-grey"> Previous</a>
                                </div>
                               
                            </form>
                        </div><!--/login form-->

                    </div>
                </div>
            </div>
        </section><!--/form-->

        <!--FOOTER-->


    </body>
</html>