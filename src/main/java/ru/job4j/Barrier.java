package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 27/02/2022 - 12:32
 */
public class Barrier {
    private static final Logger LOG = LoggerFactory.getLogger(Barrier.class);

    private boolean flag = false;

    private final Object monitor = this;

    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

