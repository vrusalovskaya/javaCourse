package org.example;

public interface Printer {

    default void print(){
        System.out.println(this);
    }
}
