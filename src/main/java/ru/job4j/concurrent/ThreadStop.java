package ru.job4j.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 09/02/2022 - 10:46
 */
public class ThreadStop {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadStop.class.getName());

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        LOG.info(String.valueOf(count++));
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
};