<html>
<head>
    <meta charset="UTF-8">
    <title>Admin / Sign Up</title>
</head>

<style>
    form {width: 400px; margin: auto}

    input[type=text], input[type=password] {
        width: 400px;
        padding: 16px;
        margin: 5px 0 25px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    input[type=text]:focus, input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }

    .container {
        padding: 16px;
    }

    .loginbtn {
        background-color: #d5a037;
        color: white;
        padding: 16px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 400px;
        opacity: 0.9;
    }

    .loginbtn:hover {
        opacity:1;
        cursor: pointer;
    }

</style>

<body>
<H1 style="text-align: center">Admin / Sign Up</H1>
<hr>
<form method="post" action="signup">
    <div class="container">
        <h1 style="text-align: center">SignUp!</h1>
        <hr>
    <table>
        <tr>
            <td></td>
            <td><input name="firstName" placeholder="First name" type="text"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input name="lastName" placeholder="Last name" type="text"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input name="phoneNumber" placeholder="Phone number" type="text"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input name="email" placeholder="Email" type="text" required /></td>
        </tr>
        <tr>
            <td></td>
            <td><input name="password" placeholder="Password" type="password" required /></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="Submit"  class="loginbtn">Send</button>
            </td>
        </tr>
    </table>
    </div>
</form>
<#--<%-->
<#--    String error = (String)request.getAttribute("signUpError");-->
<#--    if (error != null) {-->
<#--        request.removeAttribute("signUpError");-->
<#--%>-->
<#--<h1 style="text-align: center"><b>Can't create user!</b></h1>-->
<#--<%-->
<#--    }-->
<#--%>-->
</body>
</html>