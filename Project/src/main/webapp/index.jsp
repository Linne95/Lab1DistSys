<%--
  Created by IntelliJ IDEA.
  User: timd0
  Date: 2023-10-05
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Log In</title>
</head>
<body>
<% Boolean invalidLogin = (Boolean) session.getAttribute("invalidLogin"); %>
<% if ( invalidLogin != null && invalidLogin) { %>
<h1>Invalid login</h1>
<%} %>
<% Boolean nameTaken = (Boolean) session.getAttribute("nameTaken"); %>
<% if ( nameTaken != null && nameTaken) { %>
<h1>Username already taken!</h1>
<%} %>
<form action="ControllerServlet" method="get">
  <label for="username">Username:</label><br>
  <input type="text" id="username" name="username"><br>
  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password"><br>
  <input type="hidden" id="path" name="path" value="logIn">

  <!-- Log In Button -->
  <input type="submit" value="Log In" onclick="setPath('logIn');">

  <!-- Sign Up Button -->
  <input type="submit" value="Sign Up" onclick="setPath('signUp');">
</form>

<script>
  function setPath(pathValue) {
    document.getElementById('path').value = pathValue;
  }
</script>
</body>
</html>
