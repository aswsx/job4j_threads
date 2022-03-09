package ru.job4j.cache;

import ru.job4j.cache.model.Base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 09/03/2022 - 21:02
 */
public class Main {
    public static void main(String[] args) {
        Map<Integer, Base> map = new HashMap<>();
        Base base = new Base(1, 0);
        map.put(base.getId(), base);

        Base user1 = map.get(1);
        user1.setName("User 1");

        Base user2 = map.get(1);
        user1.setName("User 2");

        map.put(user1.getId(), user1);
        map.put(user2.getId(), user2);

        System.out.println(map);
    }
}
