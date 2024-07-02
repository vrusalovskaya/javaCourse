package org.example;

import java.sql.Timestamp;

public class User {
    private final int id;
    private final String name;
    private final Timestamp creationDate;

    public User(int id, String name, Timestamp creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "User {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", creationDate = " + creationDate +
                '}';
    }
}
