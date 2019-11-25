<%-- 
    Document   : index
    Created on : 15-Nov-2019, 19:46:58
    Author     : Ya Boi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <title>Login</title>
    </head>
    <body>
        <div class="outer">
            <div class="inner">
                <table>
                    <tr>
                        <td colspan="2"><h1>Welcome</h1></td>
                    </tr>

                    <form method="post" action ="Login" autocomplete="off">
                            <tr>
                                <td><input class="textbox" type="text" placeholder="Enter Username" value="" name="username" required autofocus></td>
                                <td><input class="textbox" type="password" placeholder="Enter Password" value="" name="password" required></td>
                            </tr>
                            <tr>
                                <td colspan="2"><input class="submitButton" type="submit" value="Login"></td>
                            </tr>
                            <%
                                if(request.getAttribute("error") != null){
                                    out.print("<tr>");
                                    out.print("<td colspan=\"2\">" + request.getAttribute("error") + "</td>");
                                    out.print("</tr>");
                                }
                            %>
                            <tr>
                                <td colspan="2"><a href="/XYZDriverAssociation/register.jsp" >register</a></td>
                            </tr>
                    </form>    
                </table>
            </div>
        </div>
    </body>
</html>
