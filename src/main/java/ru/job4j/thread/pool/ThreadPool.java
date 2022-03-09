package ru.job4j.thread.pool;

import ru.job4j.buffer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 09/03/2022 - 22:44
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (var thread : threads) {
            thread.interrupt();
        }
    }

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (var i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                while (Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll();
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
        for (var thread : threads) {
            thread.start();
        }
    }

    private static void poolDemo(ThreadPool threadPool) throws InterruptedException {
        threadPool.work(() -> System.out.println(Thread.currentThread().getId()));
        threadPool.work(() -> System.out.println(Thread.currentThread().getId()));
        threadPool.work(() -> System.out.println(Thread.currentThread().getId()));
        threadPool.work(() -> System.out.println(Thread.currentThread().getId()));
        threadPool.work(() -> System.out.println(Thread.currentThread().getId()));
    }

    public static void main(String[] args) throws InterruptedException {
        var threadPool = new ThreadPool();
        poolDemo(threadPool);
        poolDemo(threadPool);
        threadPool.shutdown();
    }
}

