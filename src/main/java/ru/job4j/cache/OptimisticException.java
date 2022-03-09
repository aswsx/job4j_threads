package ru.job4j.cache;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 09/03/2022 - 21:28
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}
