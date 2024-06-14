package org.example.models.users;

import org.example.models.Entity;

public abstract class User extends Entity {
    private final String name;

    public User (Integer id, String name){
        this.setId(id);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public abstract void printRole();
}
