package com.example.loginandroid_29_09_2023.beans;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String username; // Cambiado a username
    private String password;

    public User() {
        this.id = id;
        this.username = username; // Cambiado a username
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username; // Cambiado a getUsername
    }

    public void setUsername(String username) {
        this.username = username; // Cambiado a setUsername
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
