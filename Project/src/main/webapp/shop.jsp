<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mock Webshop</title>
</head>
<body>
<h1>Welcome to the shop!</h1>
<% Boolean loggedIn = (Boolean) session.getAttribute("loggedIn"); %>
<% if (loggedIn != null && loggedIn) { %>
<h5>Logged in as: <%= session.getAttribute("sessionUsername") %></h5>
<form action="ControllerServlet" method="get">
    <input type="hidden" name="path" value="logOut">
    <input type="submit" value="Log Out">
</form>
<% } else  {%>
<a href="shop.jsp">Log In</a>
<a href="signUp.jsp">Sign Up</a>
<%} %>
<br>
<form action="ControllerServlet" method="get">
    <input type="hidden" name="path" value="shoppingCart">
    <input type="submit" value="Shopping Cart">
</form>


    <ul>
        <%
            String[][] dataList = (String[][]) session.getAttribute("productList");
            for (String[] element : dataList) {
        %>
        <li><%= element[1] + ": " + element[2] + "kr" %></li>
        <form action="ControllerServlet" method="get">
            <input type="hidden" name="nutId" value="<%=element[0]%>">
            <input type="hidden" name="nutName" value="<%=element[1]%>">
            <input type="hidden" name="nutPrice" value="<%=element[2]%>">
            <input type="hidden" name="path" value="addToCart">
            <input type="submit" value="Add to Cart">
        </form>
        <%
            }
        %>
    </ul>

</body>
</html>
