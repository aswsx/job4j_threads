package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 09/03/2022 - 14:49
 */
public class CASCountTest {
    @Test
    public void whenInitialIs0AfterTripleIncrementThenCountResult3() {
        CASCount count = new CASCount(0);
        count.increment();
        count.increment();
        count.increment();
        assertThat(count.get(), is(3));
    }

    @Test
    public void when3IncrementThenGetThenResult3() {
        CASCount count = new CASCount(0);
        count.increment();
        count.get();
        count.get();
        count.increment();
        count.get();
        count.increment();
        assertThat(count.get(), is(3));
    }
}