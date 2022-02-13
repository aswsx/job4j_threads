package ru.job4j;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/02/2022 - 14:50
 */
public class Cache {
    private static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
