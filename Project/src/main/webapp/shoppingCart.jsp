<%@ page import="com.example.project.ui.CartInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.example.project.bo.Cart" %><%--
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
        Collection<CartInfo> dataList = (Collection<CartInfo>) session.getAttribute("userCart");
        Iterator<CartInfo> it = dataList.iterator();
        for (; it.hasNext();) {
            CartInfo item = it.next();
    %>
    <li><%= item.getQuantity() + " x " + item.getItemName() + ": " + item.getPrice() + "kr/st" %></li>
    <form action="ControllerServlet" method="get">
        <input type="hidden" name="path" value="removeFromCart">
        <input type="hidden" name="qt" value="<%=item.getQuantity()%>">
        <input type="hidden" name="nutId" value="<%=item.getNutId()%>">
        <input type="submit" value="Remove from Cart">
    </form>
    <%
        }
    %>
</ul>
</body>
</html>
