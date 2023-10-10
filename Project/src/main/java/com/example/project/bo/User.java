package com.example.project.bo;

import com.example.project.db.DBUser;

import java.util.Collection;

public class User {
    private String username;
    private String password;

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    static public boolean addUser(String username, String password) {
        return DBUser.addUserToDatabase(username, password);
    }

    static public boolean logIn(String username, String password) {
        return DBUser.logIn(username, password);
    }

    static public boolean authenticateUser(String username, String password) {
        return DBUser.authenticateUser(username, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
