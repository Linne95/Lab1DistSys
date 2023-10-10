package com.example.project.ui;

import com.example.project.bo.CartHandler;
import com.example.project.bo.ItemHandler;
import com.example.project.bo.UserHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "controllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username;
        String password;
        HttpSession session = request.getSession();
        switch (request.getParameter("path")) {
            case ("logIn"):
                username = request.getParameter("username");
                password = request.getParameter("password");
                session.setAttribute("invalidLogin", false);

                if (UserHandler.logIn(username, password)) {
                    session.setAttribute("sessionUsername", username);
                    session.setAttribute("sessionPassword", password);
                    session.setAttribute("loggedIn", true);
                    ArrayList<ItemInfo> fetchedProducts = (ArrayList<ItemInfo>) ItemHandler.getItems();
                    session.setAttribute("productList", fetchedProducts);
                    response.sendRedirect("http://localhost:8080/Project_war_exploded/shop.jsp");
                }
                else {
                    response.sendRedirect("http://localhost:8080/Project_war_exploded/index.jsp");
                    session.setAttribute("invalidLogin", true);
                }
            break;
            case ("signUp"):
                username = request.getParameter("username");
                password = request.getParameter("password");
                session.setAttribute("nameTaken", false);
                if(UserHandler.singUp(username, password)){
                    ArrayList<ItemInfo> fetchedProducts = (ArrayList<ItemInfo>) ItemHandler.getItems();
                    session.setAttribute("productList", fetchedProducts);
                    session.setAttribute("sessionUsername", username);
                    session.setAttribute("sessionPassword", password);
                    session.setAttribute("loggedIn", true);
                    response.sendRedirect("http://localhost:8080/Project_war_exploded/shop.jsp");
                }else {
                    session.setAttribute("nameTaken", true);
                    response.sendRedirect("http://localhost:8080/Project_war_exploded/index.jsp");
                }
            break;
            case ("logOut"):
                session.removeAttribute("sessionUsername");
                session.setAttribute("loggedIn", false);
                session.setAttribute("nameTaken", false);
                response.sendRedirect("http://localhost:8080/Project_war_exploded/index.jsp");
            break;
            case ("shoppingCart"):
                ArrayList<CartInfo> returnedUsersCart = (ArrayList<CartInfo>) CartHandler.getShoppingCart((String) session.getAttribute("sessionUsername"));
                session.setAttribute("userCart", returnedUsersCart);
                response.sendRedirect("http://localhost:8080/Project_war_exploded/shoppingCart.jsp");
            break;
            case ("addToCart"):
                int itemId = Integer.parseInt(request.getParameter("nutId"));
                if(UserHandler.authenticateUser((String) session.getAttribute("sessionUsername"), (String) session.getAttribute("sessionPassword"))) {
                    CartHandler.addItemToCart((String) session.getAttribute("sessionUsername"), (String) session.getAttribute("sessionPassword"), itemId);
                }
                response.sendRedirect("http://localhost:8080/Project_war_exploded/shop.jsp");
            break;
            case ("removeFromCart"):
                CartHandler.removeItemFromCart((String) session.getAttribute("sessionUsername"),Integer.parseInt(request.getParameter("qt")), Integer.parseInt(request.getParameter("nutId")));
                response.sendRedirect("http://localhost:8080/Project_war_exploded/shoppingCart.jsp");
            break;
            case ("backToShop"):
                response.sendRedirect("http://localhost:8080/Project_war_exploded/shop.jsp");
            break;
        }
    }
}
