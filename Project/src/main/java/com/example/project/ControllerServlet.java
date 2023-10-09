package com.example.project;

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
        //session.setAttribute("loggedIn", false);
        //session.setAttribute("invalidLogin", false);
        switch (request.getParameter("path")) {
            case ("logIn"):
                // Retrieve item details from the form
                username = request.getParameter("username");
                password = request.getParameter("password");
                session.setAttribute("invalidLogin", false);

                // Print the product information to the console
                System.out.println("user: " + username + " , password: " + password);
                ArrayList<String[]> returnedUsersCart = DBItemManager.logIn(username, password);

                if (returnedUsersCart == null || returnedUsersCart.equals("null")) {
                    response.sendRedirect("http://localhost:8080/Project_war_exploded/index.jsp");
                    session.setAttribute("invalidLogin", true);
                }
                else {
                    session.setAttribute("sessionUsername", username);
                    session.setAttribute("sessionPassword", password);
                    session.setAttribute("loggedIn", true);

                    String[][] currentUsersCart = new String[returnedUsersCart.size()][];
                    for (int i = 0; i < returnedUsersCart.size(); i++) {
                        currentUsersCart[i] = returnedUsersCart.get(i);
                    }

                    session.setAttribute("userCart", currentUsersCart);

                    ArrayList<String[]> fetchedProducts = DBItemManager.getProducts();

                    String[][] string2DArray = new String[fetchedProducts.size()][];
                    for (int i = 0; i < fetchedProducts.size(); i++) {
                        string2DArray[i] = fetchedProducts.get(i);
                    }

                    session.setAttribute("productList", string2DArray);
                    // Redirect back to the index.jsp page
                    response.sendRedirect("http://localhost:8080/Project_war_exploded/shop.jsp");
                }
            break;
            case ("signUp"):
                // Retrieve item details from the form
                username = request.getParameter("username");
                password = request.getParameter("password");
                session.setAttribute("nameTaken", false);
                // Print the product information to the console
                System.out.println("Sign up: user:: " + username + " , password: " + password);
                if(DBItemManager.addUser(username, password)){
                    session.setAttribute("sessionUsername", username);
                    session.setAttribute("sessionPassword", password);
                    session.setAttribute("loggedIn", true);
                    response.sendRedirect("http://localhost:8080/Project_war_exploded/shop.jsp");
                }else {
                    // Redirect back to the index.jsp page
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
                // Redirect back to the index.jsp page
                response.sendRedirect("http://localhost:8080/Project_war_exploded/shoppingCart.jsp");
            break;
            case ("addToCart"):
                // Print the product information to the console
                System.out.println("You have added: " + request.getParameter("nutName"));
                int itemId = Integer.parseInt(request.getParameter("nutId"));
                String itemName = request.getParameter("nutName");
                String itemPrice = request.getParameter("nutPrice");

                DBItemManager.addItemToCart((String) session.getAttribute("sessionUsername"), (String) session.getAttribute("sessionPassword"), itemId);

                response.sendRedirect("http://localhost:8080/Project_war_exploded/shop.jsp");
            break;
            case ("backToShop"):
                response.sendRedirect("http://localhost:8080/Project_war_exploded/shop.jsp");
            break;
        }
    }
}
