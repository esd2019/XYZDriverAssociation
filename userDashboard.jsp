<%-- 
    Document   : userDashboard.jsp
    Created on : 16-Nov-2019, 02:32:04
    Author     : Ya Boi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Home</title>
    </head>
    <body>
        <div class="outer">
            <div class="inner">
                <table>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="Check for outstanding balance"/></form></td></tr>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="Make a payment"/></form></td></tr>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="Submit a claim"/></form></td></tr>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="List all claims and payments to date"/></form></td></tr>
                    <tr><td><form action="Logout"><input class="submitButton" type="submit" value="Logout"/></form></td></tr>
                </table>
            </div>
        </div>
    </body>
</html>
