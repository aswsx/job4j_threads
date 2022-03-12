package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 12/03/2022 - 12:43
 */
public class ParallelIndexFinder extends RecursiveTask<Integer> {
    private final Integer[] array;
    private final Integer sample;
    private final Integer from;
    private final Integer to;

    public ParallelIndexFinder(Integer[] array, Integer sample, Integer from, Integer to) {
        this.array = array;
        this.sample = sample;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (from - to <= 10) {
            for (var i = 0; i < array.length; i++) {
                if (array[i].equals(sample)) {
                    return i;
                }
            }
            return -1;
        }

        var mid = (from + to) / 2;
        var left = new ParallelIndexFinder(array, sample, from, mid);
        var right = new ParallelIndexFinder(array, sample, mid + 1, to);
        left.fork();
        right.fork();
        return Math.max(left.join(), right.join());
    }

    public static Integer findIndex(Integer[] array, Integer sample) {
        var forkJoinPool = new ForkJoinPool();
        return forkJoinPool
                .invoke(new ParallelIndexFinder(array, sample, 0, array.length - 1));
    }
}
