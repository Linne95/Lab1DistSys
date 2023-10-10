package com.example.project.bo;

public class UserHandler {
    public static boolean logIn(String username, String password){
        return User.logIn(username, password);
    }

    public static boolean singUp(String username, String password){
        return User.addUser(username, password);
    }

    public static boolean authenticateUser(String username, String password) {
        return User.authenticateUser(username, password);
    }
}
