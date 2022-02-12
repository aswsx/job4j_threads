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
        Thread progress = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            LOG.info("start ...");
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            LOG.info(String.valueOf(Thread.currentThread().isInterrupted()));
                            LOG.info(String.valueOf(Thread.currentThread().getState()));
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
        progress.join();
    }
}