package ru.job4j.storage.model;

import java.util.Objects;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 14/02/2022 - 20:51
 * <p>
 * Класс- модель пользователя
 * Содержит поля id, amount
 * Конструктор
 * Геттеры и сеттеры
 * Переопределенные методы equals(), hashCode(), toString()
 */
public class User {
    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return String.format("User (id=%s, amount=%s)", this.id, this.amount);
    }
}
