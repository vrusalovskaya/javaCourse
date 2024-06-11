package org.example;

public abstract class User {
    private final String name;

    public User (String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public abstract void printRole();
}
