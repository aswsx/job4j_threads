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
    public void whenLinearSearchAndNotFoundElement() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int result = new ParallelIndexFinder<Integer>().findIndex(array, 12);
        assertThat(result, is(-1));
    }

    @Test
    public void whenLinearSearchAndFoundElement() {
        Integer[] array = {1, 5, 8, 6, 3, 10, 11, 2, 9};
        int result = new ParallelIndexFinder<Integer>().findIndex(array, 9);
        assertThat(result, is(8));
    }

    @Test
    public void whenForkJoinSearchAndFoundElement() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int result = new ParallelIndexFinder<Integer>().findIndex(array, 9);
        assertThat(result, is(8));
    }

    @Test
    public void whenForkJoinSearchAndNotFoundElement() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int result = new ParallelIndexFinder<Integer>().findIndex(array, 15);
        assertThat(result, is(-1));
    }

    @Test
    public void whenForkJoinSearchCharAndFoundElement() {
        Character[] array = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
        int result = new ParallelIndexFinder<Character>().findIndex(array, 'c');
        assertThat(result, is(2));
    }
}