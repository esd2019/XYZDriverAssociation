
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secure information page</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    </head>

    <script>
        function randomPassword(length) {
            var chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOP1234567890";
            var pass = "";
            for (var x = 0; x < length; x++) {
                var i = Math.floor(Math.random() * chars.length);
                pass += chars.charAt(i);
            }
            return pass;
        }

        function generate() {
            myform.password.value = randomPassword(myform.length.value);
        }


    </script>
    <body>
        <form name="myform" method="post" action="memberRegister">
            <input type="hidden" name="length" value="10">
            <table width="100%" border="0" class="signup-form">
                <tr>
                    <td width="120"> Full name: </td>
                    <td>
                        <p id="err1" style="color: red"></p>
                        <input type="text" size="40"  name="fullname" id="fullname" value="" pattern=".{6,}" title="Six or more character" required/>
                    </td>
                </tr>

                <tr>
                    <td width="120"> Email address: </td>
                    <td>
                        <p id="err3" style="color: red"></p>
                        <input type="text" size="40" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" name="email" id="email" value="" oninput="checkEmail()"required/>

                    </td>
                </tr>

                <tr>
                    <td width="120"> Day of Birth: </td>
                    <td>
                        <p id="err4" style="color: red"></p>
                        <input type="text" name="dob" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" id="dob" value="" required/>

                    </td>
                </tr>

                <tr>
                    <td width="120"> No. Street: </td>
                    <td>
                        <p id="err5" style="color: red"></p>
                        <input type="text" size="40" placeholder="No. Street" name="street" id="address" value="" oninput="checkStreet()"required/>

                    </td>
                </tr>
                <tr>
                    <td width="120"> Area: </td>
                    <td>
                        <p id="err6" style="color: red"></p>
                        <input type="text" size="40" placeholder="Area" name="area" id="address" value="" oninput="checkArea()"required/>

                    </td>
                </tr>
                <tr>
                    <td width="120"> Town/City: </td>
                    <td>
                        <p id="err7" style="color: red"></p>
                        <input type="text" size="40" placeholder="Town/City" name="city" id="address" value="" oninput="checkCity()"required/>

                    </td>
                </tr>
                <tr>
                    <td width="120"> County: </td>
                    <td>
                        <p id="err8" style="color: red"></p>
                        <input type="text" size="40" placeholder="County" name="county" id="address" value="" oninput="checkCounty()"required/>

                    </td>
                </tr>
                <tr>
                    <td width="120"> Postcode: </td>
                    <td>
                        <p id="err9" style="color: red"></p>
                        <input type="text" size="40" placeholder="Postcode" name="postcode" id="address" value="" oninput="checkPostcode()"required/>

                    </td>
                </tr>



                <tr>
                    <td width="120"> Fee: </td>
                    <td>
                        <label for="fee"size="40"> £10</label>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>

                    <td>
                        <input name="password" type="text" size="40" value="" oninput="checkPassword()"  title="Press to generate password" required/>&nbsp;
                        <input type="button" class="w3-button w3-green" value="Generate Password" onClick="generate();" tabindex="4">
                    </td>
                </tr>
                <tr>
                    <td> Account number: </td>
                    <td>
                        <input name="account" type="text" size="40"value=""  pattern="[0-9]+" title="Number only" required/>
                    </td>

                </tr>

                <tr>
                    <td> Security Code: </td>
                    <td>
                        <input name="code" type="text" size="10" value="" required pattern="[0-9]+" title="Number only" required/>
                    </td>

                </tr>

                <tr>
                    <td> Expiration Month
                        <select name="month" value="" required>
                            <option value=""> Select Month</option>
                            <option value="1"> January</option>
                            <option value='2'>February</option>
                            <option value='3'>March</option>
                            <option value='4'>April</option>
                            <option value='5'>May</option>
                            <option value='6'>June</option>
                            <option value='7'>July</option>
                            <option value='8'>August</option>
                            <option value='9'>September</option>
                            <option value='10'>October</option>
                            <option value='11'>November</option>
                            <option value='12'>December</option>
                        </select>    
                    </td>
                </tr>
                <tr>    
                    <td> Expiration Year
                        <select name="year" value="" required>
                            <option value=""> Select year</option>
                            <option value="2019"> 2019</option>
                            <option value='2020'>2020</option>
                            <option value='2021'>2021</option>
                            <option value='2022'>2022</option>
                            <option value='2023'>2023</option>
                            <option value='2024'>2024</option>                           
                        </select>    
                    </td>
                </tr>
            </table>
            <input type="hidden" name="action"  value="Signup"/>
            <button class="w3-button w3-indigo" size="20" type="submit">Sign Up</button>
            <div>
                <a href="homepage.jsp" class="w3-button w3-left w3-light-grey"> Previous</a>
            </div>

        </form>
    </body>
</html>
