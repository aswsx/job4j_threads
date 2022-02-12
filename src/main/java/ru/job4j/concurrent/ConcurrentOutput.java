package ru.job4j.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 12/02/2022 - 18:43
 */
public class ConcurrentOutput {
    private static final Logger LOG = LoggerFactory.getLogger(ConcurrentOutput.class.getName());

    public static void main(String[] args) {
        Thread another = new Thread(
                () -> LOG.info(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> LOG.info(Thread.currentThread().getName())
        );
        another.start();
        second.start();
        LOG.info(Thread.currentThread().getName());
    }
}
