package ru.job4j.thread.email.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 10/03/2022 - 14:47
 */
public class User {
    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    private String name;

    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "User (name=%s, email=%s)", this.name, this.email);
    }
}
