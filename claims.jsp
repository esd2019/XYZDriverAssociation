<%-- 
    Document   : claims
    Created on : 21-Nov-2019, 15:21:31
    Author     : ninhthelam
--%>

<%@page import="model.User"%>
<%@page import="dao.UserDAOImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make claims page</title>
    </head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <%
        HttpSession s = request.getSession();
        String rational_err = ""; String date_err=""; String amount_err =""; String email_err="";
        User user = new User();
        
        if (request.getAttribute("email_err") != null) {
            email_err = (String) request.getAttribute("email_err");
        }

        if (request.getAttribute("rational_err") != null) {
            rational_err = (String) request.getAttribute("rational_err");
        }
        if (request.getAttribute("date_err") != null) {
            date_err = (String) request.getAttribute("date_err");
        }
        if (request.getAttribute("amount_err") != null){
            amount_err =(String) request.getAttribute(amount_err);  
        }
        

        String rational = "", date = "", amount = "",  email="";

        if (request.getAttribute("rational") != null) {
            rational = (String) request.getAttribute("rational");
        }
        if (request.getAttribute("date") != null) {
            date = (String) request.getAttribute("date");
        }

        if (request.getAttribute("amount") != null) {
            amount = (String) request.getAttribute("amount");
        }
        if (request.getAttribute("email") != null) {
            email = (String) request.getAttribute("email");
        }
         else {
            email = (String) s.getAttribute("email");
            user = new UserDAOImpl().getUserDetailByEmail(email);
        }

    %>
    
    <body>
        <form name="myform" method="post" action="AccountController">
            <input type="hidden" name="length" value="10">
            <table width="100%" border="0" class="signup-form">
                <tr>
                    <td width="120"> Email address: </td>
                    <td>
                        <p style="color: red"><%= email_err%></p>
                        <p id="err3" style="color: red"></p>
                        <input type="text" size="40" placeholder="Email" name="email" id="email" value="<%= email.equals("") ? user.getEmail() : email%>" oninput="checkEmail()"/>

                    </td>
                </tr>

                <tr>
                    <td width="120"> Rationale: </td>
                    <td>
                        <p style="color: red"><%= rational_err%></p>
                        <p id="err1" style="color: red"></p>
                        <input type="text" size="40"  name="rational" id="rational" value="<%= rational%>" oninput="checkFullname()"/>
                    </td>
                </tr>

                <tr>
                    <td width="120"> Date: </td>
                    <td>
                        <p style="color: red"><%= date_err%></p>
                        <p id="err3" style="color: red"></p>
                        <input type="text" size="40"  name="date" id="date" value="<%= date%>" oninput="checkEmail()"/>

                    </td>
                </tr>

                <tr>
                    <td width="120">Amount £: </td>
                    <td>
                         <p style="color: red"><%= amount_err%></p>
                        <input  type="text" size="20" name="amount" id="amount" value="<%= amount%>" oninpt="checkEmail()"/>
                    </td>
                </tr> 

               
    
            </table>
                    <br></br>
            <input type="hidden" name="action"  value="claim"/>
            <button class="w3-button w3-indigo" size="20" type="submit">Submit Claim Form</button>
            <div>
                <button class="w3-button w3-left w3-light-grey"> Previous</button>
            </div>
        </form>
    </body>
</html>
