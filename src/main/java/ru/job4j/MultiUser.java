package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 27/02/2022 - 12:38
 */
public class MultiUser {
    private static final Logger LOG = LoggerFactory.getLogger(MultiUser.class);

    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    LOG.info(String.format("Started %s", Thread.currentThread().getName()));
                    barrier.on();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check();
                    LOG.info(String.format("Started %s", Thread.currentThread().getName()));
                },
                "Slave"
        );
        master.start();
        slave.start();
    }
}
