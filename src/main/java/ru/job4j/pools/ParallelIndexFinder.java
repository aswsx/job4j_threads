package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 12/03/2022 - 12:43
 */
public class ParallelIndexFinder<T> extends RecursiveTask<Integer> {
    private transient T[] array;
    private transient T sample;
    private int from;
    private int to;

    public ParallelIndexFinder(T[] array, T sample, int from, int to) {
        this.array = array;
        this.sample = sample;
        this.from = from;
        this.to = to;
    }

    public ParallelIndexFinder(T[] array, T sample) {
        this.array = array;
        this.sample = sample;
        this.from = 0;
        this.to = array.length - 1;
    }

    public ParallelIndexFinder() {
    }

    public Integer findIndex(T[] array, T sample) {
        return new ForkJoinPool()
                .invoke(new ParallelIndexFinder<>(array, sample));
    }

    private int linearSearch() {
        for (var i = from; i <= to; i++) {
            if (array[i].equals(sample)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if (from - to <= 10) {
            return linearSearch();
        }
        var mid = (from + to) / 2;
        ParallelIndexFinder<T> left = new ParallelIndexFinder<>(array, sample, from, mid);
        ParallelIndexFinder<T> right = new ParallelIndexFinder<>(array, sample, mid + 1, to);
        left.fork();
        right.fork();
        return Math.max(left.join(), right.join());
    }
}
