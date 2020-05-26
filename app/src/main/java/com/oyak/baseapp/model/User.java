package com.oyak.baseapp.model;

import java.io.Serializable;

public class User implements Serializable {

    public String name;
    public String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
