package ru.job4j.storage;

import ru.job4j.storage.model.User;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 14/02/2022 - 21:08
 *
 * Интерфейс Store
 * содержит шаблоны методов для использовании в классе UserStorage
 */
public interface Store {
    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    void transfer(int fromId, int toId, int amount);
}
