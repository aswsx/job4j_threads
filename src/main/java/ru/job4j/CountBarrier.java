package ru.job4j;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 27/02/2022 - 12:45
 */
public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            ++count;
            notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    monitor.wait();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
