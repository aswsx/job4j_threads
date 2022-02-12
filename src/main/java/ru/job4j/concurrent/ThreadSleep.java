package ru.job4j.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 12/02/2022 - 23:10
 */
public class ThreadSleep {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadSleep.class);

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        LOG.info("Start loading ... ");
                        Thread.sleep(3000);
                        LOG.info("Loaded.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        LOG.info("Main");
    }
}

