<%@ page import="models.UserModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory" %>
<%@ page import="javax.xml.parsers.DocumentBuilder" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.w3c.dom.NodeList" %>
<%@ page import="org.w3c.dom.Node" %>
<%@ page import="org.w3c.dom.Element" %>
<%@ page import="java.io.File" %>
<%@ page import="javax.xml.parsers.ParserConfigurationException" %>
<%@ page import="org.xml.sax.SAXException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  boolean isLoggedIn = false;
  UserModel user = null;
  session.setAttribute("file", "index.jsp");
  try {
    isLoggedIn = (boolean) session.getAttribute("loggedIn");
  }
  catch(Exception e) {

  }

  String message = "";
  if (isLoggedIn) {

    user = (UserModel) session.getAttribute("user");
    message = user.getUsername();
  }
  else {
    message = "logged out";
  }
%>

<!--Merry added this -->
<%

  //int groupID= (int) request.getAttribute("group");
  // Get the XML file and its contents
  File xmlFile = new File("C:\\Users\\wyatt\\Box\\Spring 2023\\Web app\\FinalProject\\ProjectFiles\\HobbyHelper1\\src\\main\\Webapp\\xml\\group.xml"); // **** MIGHT HAVE TO CHANGE PATH ****
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = null;
  try {
    db = dbf.newDocumentBuilder();
  } catch (ParserConfigurationException e) {
    throw new RuntimeException(e);
  }
  Document d = null;
  try {
    d = db.parse(xmlFile);
  } catch (SAXException e) {
    throw new RuntimeException(e);
  }
  d.getDocumentElement().normalize();
  NodeList groupsNL = d.getElementsByTagName("Group");
%>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>HobbyHelper</title>
  <!-- Favicon-->
  <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="css/styles.css" rel="stylesheet" />
</head>
<body>
<!-- Responsive navbar-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="#!">Hobby Helper</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="Login.jsp">Login</a></li>
        <li class="nav-item"><a class="nav-link" href="FetchActivitiesServlet" action="FetchActivitiesServlet" method="post" onclick="return validate()">Register </a></li>
        <li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
      </ul>
    </div>
    <p ><h2 style="color:White; font-family: Verdana "><%=message%></h2></p>

  </div>

</nav>
<!-- Page content-->
<div class="container mt-5">
  <div class="row">
    <div class="col-lg-12">
      <!-- Post content-->
      <article>
        <!-- Post header-->
        <header class="mb-4">
          <!-- Post title-->
          <h1 class="fw-bolder mb-1">Welcome to the baseball group page!  </h1>
          <!-- Post meta content-->

        </header>
        <!-- Preview image figure-->
        <figure class="mb-4"><img class="img-fluid rounded" src="assets/baseball.jpeg" alt="..." /></figure>
        <!-- Post content-->
        <section class="mb-5">
          <p class="fs-5 mb-4">Everyone has hobbies and everyone has friends but not everyone is lucky enough to have friends that share their hobbies!</p>
          <p class="fs-5 mb-4">That is where HobbyHelper come in to help! Let us match you with future friends at Auburn University that you can safely contact via their university email!</p>
          <p class="fs-5 mb-4">We hope that you are now able to enjoy your new-found friendships while participating in your favorite hobbies!</p>
          <!--<h2 class="fw-bolder mb-4 mt-5">I have odd cosmic thoughts every day</h2>
          <p class="fs-5 mb-4">For me, the most fascinating interface is Twitter. I have odd cosmic thoughts every day and I realized I could hold them to myself or share them with people who might be interested.</p>
          <p class="fs-5 mb-4">Venus has a runaway greenhouse effect. I kind of want to know what happened there because we're twirling knobs here on Earth without knowing the consequences of it. Mars once had running water. It's bone dry today. Something bad happened there as well.</p>
     -->
        </section>
      </article>
      <!-- Group content -->


    </div>
    <!-- Side widgets-->
  </div>
</div>
<!-- Footer-->
<footer class="py-5 bg-dark">
  <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
