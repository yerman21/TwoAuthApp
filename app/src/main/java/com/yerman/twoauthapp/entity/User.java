package com.yerman.twoauthapp.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static String username;
    private static String pass;
    private static String names;
    private static String apellidos;
    private static String phone;

    public User(String username, String pass, String names, String apellidos, String phone) {
        this.username = username;
        this.pass = pass;
        this.names = names;
        this.apellidos = apellidos;
        this.phone = phone;
    }
    public User(){}

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        User.pass = pass;
    }

    public static String getNames() {
        return names;
    }

    public static void setNames(String names) {
        User.names = names;
    }

    public static String getApellidos() {
        return apellidos;
    }

    public static void setApellidos(String apellidos) {
        User.apellidos = apellidos;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        User.phone = phone;
    }
}
