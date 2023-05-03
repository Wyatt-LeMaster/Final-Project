<!--
*
*  JSP Assignment 2
*  Wyatt LeMaster
*  5/2/2023
*  JSP to display the register page
*
*
-->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sessions and Cookies</title>
    <link href="<c:url value="css/sign-in.css" />" rel="stylesheet">
    <link href="<c:url value="css/headers.css" />" rel="stylesheet">

    <link href="headers.css" rel="stylesheet">
    <link href="<c:url value="css/bootstrap.min.css" />" rel="stylesheet">

</head>
<body class = "text-center">

<main class ="form-signin w-100 m-auto">


    <div class = "fixed-top">
        <div class="container">
            <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
                <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
                    <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
                    <span class="fs-4">Library Login</span>
                </a>

                <ul class="nav nav-pills">
                    <li class="nav-item"><a href="findAFriend.jsp" class="nav-link " aria-current="page">Home</a></li>
                    <li class="nav-item"><a href="register.jsp" class="nav-link active" aria-current="page">register</a></li>
                    <li class="nav-item"><a href="Login.jsp" class="nav-link " aria-current="page">Login</a></li>
                    <li class="nav-item"><a href="LogoutServlet" action="LogoutServlet" method="post" onclick="return validate()" class="nav-link" aria-current="page">Logout</a></li>
                    <li class="nav-item"><a href="ReserveNavServlet" action="ReserveNavServlet" method="post" onclick="return validate()" class="nav-link" aria-current="page">Reservations</a></li>

                </ul>
            </header>
        </div>
    </div>



<form action="registerServlet" method="post" onsubmit="return validate()">
    <h1 class="h3 mb-3 fw-normal">Register</h1>

    <div class="form-floating">
        <input id="username" name="username" type="text" class = "form-control" placeholder="username"/>
        <label for="username">Username</label>
    </div>

    <div class="form-floating">
        <input id="email" name="email" type="text" class = "form-control" placeholder="email"/>
        <label for="email">email</label>
    </div>

    <div class="form-floating">

        <input id="password" name="password" type="password" class = "form-control" placeholder="password"/>
        <label for="password">Password</label>
    </div>

    <div class="form-floating">

        <input id="passwordCON" name="passwordCON" type="password" class = "form-control" placeholder="Password verify"/>
        <label for="passwordCON">Password verify</label>
    </div>

    <p style="color:red; font-family: Verdana ">${error}</p>


    <div class="form-floating">

        <input id="firstName" name="firstName" type="text" class = "form-control" placeholder="First Name"/>
        <label for="firstName">First name</label>
    </div>

    <div class="form-floating">

        <input id="lastName" name="lastName" type="text" class = "form-control" placeholder="Last Name"/>
        <label for="lastName">Last Name</label>
    </div>

    </br>

    <div class="d-grid gap-2 d-sm-flex justify-content-sm-center mb-5">

        <div class="overflow-scroll" style="max-height: 30vh;">

            <div class="container px-5">
                <h1 class="h3 mb-3 fw-normal">Select all that apply</h1>

                    <table>

                    <tr>
                        <th></th>
                        <th>Activity Name</th>
                    </tr>

                    <c:forEach var="each_activity" items="${list_of_activities}">
                        <tr>
                            <td><input type="checkbox" id="${each_activity.getActivityID()}" name="Checkbox ${each_activity.getActivityID()}" value="${each_activity.getActivityID()}"></td>
                            <td>${each_activity.getName()}</td>

                        </tr>
                    </c:forEach>
                </table>

            </div>


        </div>
    </div>
    <input class="w-100 btn btn-lg btn-primary"  type="submit" name="Login" value="Register" /> <br/> </br>
    <span id="error_msg"></span>

</form>





</main>
</body>
</html>