<%--
  Created by IntelliJ IDEA.
  User: timd0
  Date: 2023-10-06
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<h1>Your shopping Cart</h1>
<form action="ControllerServlet" method="get">
    <input type="hidden" name="path" value="backToShop">
    <input type="submit" value="Back to Shop">
</form>
<ul>
    <%
        String[][] dataList = (String[][]) session.getAttribute("userCart");
        for (String[] element : dataList) {
    %>
    <li><%= element[1] + ": " + element[2] + "kr" %></li>
    <%
        }
    %>
</ul>
</body>
</html>
