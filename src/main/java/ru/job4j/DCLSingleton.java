package ru.job4j;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 13/02/2022 - 14:56
 */
public class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }

}
