package com.yerman.twoauthapp.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String pass;
    private String names;
    private String apellidos;
    private Long phone;

    public User(String username, String pass, String names, String apellidos, Long phone) {
        this.username = username;
        this.pass = pass;
        this.names = names;
        this.apellidos = apellidos;
        this.phone = phone;
    }
    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
