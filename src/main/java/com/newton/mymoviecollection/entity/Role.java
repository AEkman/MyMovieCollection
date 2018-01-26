package com.newton.mymoviecollection.entity;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}