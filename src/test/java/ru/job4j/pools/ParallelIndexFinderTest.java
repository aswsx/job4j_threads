package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 12/03/2022 - 13:26
 */
public class ParallelIndexFinderTest {

    @Test
    public void whenLinearSearchNotFoundElement() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int result = ParallelIndexFinder.findIndex(array, 12);
        assertThat(result, is(-1));
    }

    @Test
    public void whenLinearSearchFoundElement() {
        Integer[] array = {1, 5, 8, 6, 3, 10, 11, 2, 9};
        int result = ParallelIndexFinder.findIndex(array, 9);
        assertThat(result, is(8));
    }

    @Test
    public void whenForkJoinSearchFoundElement() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int result = ParallelIndexFinder.findIndex(array, 9);
        assertThat(result, is(8));
    }

    @Test
    public void whenForkJoinSearchNotFoundElement() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int result = ParallelIndexFinder.findIndex(array, 15);
        assertThat(result, is(-1));
    }
}