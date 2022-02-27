package ru.job4j.buffer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 27/02/2022 - 13:53
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleBlockingQueue.class);
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue() {
        limit = 5;
    }

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == limit) {
            LOG.info("Queue is full, waiting for free");
            this.wait();
        }
        queue.add(value);
        LOG.info(String.format("Add OK %s", value.toString()));
        this.notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            LOG.info("Queue is empty, waiting until put");
            this.wait();
        }
        var rsl = queue.poll();
        LOG.info(String.format("Take OK %s ", rsl));
        this.notifyAll();
        return rsl;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>();
        while (Thread.currentThread().isInterrupted()) {
            sbq.offer(1);
        }
    }
}
