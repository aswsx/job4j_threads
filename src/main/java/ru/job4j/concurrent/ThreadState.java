package ru.job4j.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 12/02/2022 - 22:36
 */
public class ThreadState {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadState.class);

    public static void main(String[] args) {
        Thread first = new Thread(
                () -> LOG.info(String.valueOf(Thread.currentThread().getName()))
        );
        Thread second = new Thread(
                () -> LOG.info(String.valueOf(Thread.currentThread().getName()))
        );

        LOG.info(String.valueOf(first.getState()));
        first.start();
        LOG.info(String.valueOf(second.getState()));
        second.start();

        while (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED) {
            LOG.info(String.valueOf(first.getState()));
            LOG.info(String.valueOf(second.getState()));
        }
        LOG.info(String.valueOf(first.getState()));
        LOG.info(String.valueOf(second.getState()));
    }
}

