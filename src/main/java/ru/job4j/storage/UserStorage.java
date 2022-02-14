package ru.job4j.storage;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.storage.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 14/02/2022 - 20:41
 * <p>
 * Класс UserStorage
 * реализует методы интерфейса Store
 * хранение пользователей осуществляется в HashMap, ключом в которой является id пользователя,
 * значением объект user
 */
@ThreadSafe
public class UserStorage implements Store {
    private final Map<Integer, User> users = new HashMap<>();
    private static final String MESSAGE = "User not exist";

    /**
     * В методе add() осуществляется проверка добавляемого пользователя на null, после чего пользователь
     * добавляется в мапу и метод возвращает true, если пользователя с таким id  в мапе не было, либо false, если
     * пользователь с таким id  уже есть в мапе и поэтому добавление нового не удалось
     *
     * @param user добавляемый пользователь
     * @return возвращаемый результат
     */
    public synchronized boolean add(User user) {
        if (user == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return users.putIfAbsent(user.getId(), user) == null;
    }

    /**
     * В методе update() осуществляется проверка изменяемого пользователя на null, после чего пользователь
     * заменяется в мапу и метод возвращает true, если пользователь с таким id  в мапе найден и заменен,
     * либо false, если пользователя с таким id  нет в мапе и поэтому заменить его не удалось
     *
     * @param user изменяемый пользователь
     * @return возвращаемый результат
     */
    public synchronized boolean update(User user) {
        if (user == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        return users.replace(user.getId(), user) != null;
    }

    /**
     * В методе update() осуществляется удаление пользователя из мапы и метод возвращает true, если пользователь
     * с таким id  в мапе найден и удален, либо false, если пользователя с таким id нет в мапе и поэтому
     * удалить его не удалось
     *
     * @param user изменяемый пользователь
     * @return возвращаемый результат
     */
    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    /**
     * В методе transfer() осуществляется проверка пользователя, осуществляющего перевод и принимающего
     * перевод на null, после чего выполняется проверка достаточного для перевода количества средств.
     * После выполнения проверок осуществляется перевод средств с акаунта отправителя на аккаунт получателя
     *
     * @param fromId id  отправителя
     * @param toId id получателя
     * @param amount размер перевода
     */
    public synchronized void transfer(int fromId, int toId, int amount) {
        User payer = users.get(fromId);
        User recipient = users.get(toId);
        if (payer == null || recipient == null) {
            throw new IllegalArgumentException(MESSAGE);
        }
        if (payer.getAmount() < amount) {
            throw new IllegalArgumentException("User amount not enough to transfer");
        }
        payer.setAmount(payer.getAmount() - amount);
        recipient.setAmount(recipient.getAmount() + amount);
    }
}
