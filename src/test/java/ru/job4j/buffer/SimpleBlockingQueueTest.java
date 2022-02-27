package ru.job4j.buffer;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 27/02/2022 - 14:25
 */
public class SimpleBlockingQueueTest {

    @Test
    public void whenQueueIsFullThenElementsQuantityEqualsSizeLimit() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(5);
        var producer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    sbq.offer(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.start();
        Thread.sleep(5);
        producer.interrupt();
        producer.join();
        assertThat(sbq.size(), is(5));
    }

    @Test
    public void whenAddAndPollIsSuccessfulThenInAndOutValuesEquals() throws InterruptedException {
        var limit = 1;
        AtomicInteger rsl = new AtomicInteger();
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(limit);
        var producer = new Thread(() -> {
            try {
                sbq.offer(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        var consumer = new Thread(() -> {
            try {
                rsl.set(sbq.poll());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(rsl.intValue(), is(5));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}