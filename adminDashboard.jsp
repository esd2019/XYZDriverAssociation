<%-- 
    Document   : adminDashboard
    Created on : 17-Nov-2019, 21:55:04
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
                    <tr><td><form action=""><input class="submitButton" type="submit" value="List all members"/></form></td></tr>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="List all outstanding balances"/></form></td></tr>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="List all claims"/></form></td></tr>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="List all provisional member applications"/></form></td></tr>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="Process individual claims"/></form></td></tr>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="Process membership applications"/></form></td></tr>
                    <tr><td><form action=""><input class="submitButton" type="submit" value="Suspend/resume membership"/></form></td></tr>
                    <tr><td><form action="Logout"><input class="submitButton" type="submit" value="Logout"/></form></td></tr>
                </table>
            </div>
        </div>
    </body>
</html>
