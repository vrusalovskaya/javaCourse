package org.example;

public interface Printable {

    default void print(){
        System.out.println("Default implementation for " + this + "object");
    }
}
