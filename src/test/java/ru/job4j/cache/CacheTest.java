package ru.job4j.cache;

import org.junit.Test;
import ru.job4j.cache.model.Base;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 09/03/2022 - 22:00
 */
public class CacheTest {

    @Test
    public void whenAddTheSameThenFalse() {
        Base base = new Base(1, 1);
        Cache cache = new Cache();
        cache.add(base);
        assertFalse(cache.add(base));
    }

    @Test
    public void whenAddToEmptyThenTrue() {
        Base base = new Base(1, 1);
        Cache cache = new Cache();
        assertTrue(cache.add(base));
    }

    @Test
    public void whenAddThenUpdate() {
        Base base = new Base(1, 1);
        base.setName("First");
        Base base2 = new Base(1, 1);
        base2.setName("Second");
        Cache cache = new Cache();
        assertTrue(cache.add(base));
        assertTrue(cache.update(base2));
        assertThat(cache.get(1).getName(), is("Second"));
    }

    @Test
    public void whenAddToEmptyThenUpdateTheSameThenVersionChanged() {
        Base base = new Base(1, 1);
        Cache cache = new Cache();
        cache.add(base);
        cache.update(base);
        assertThat(cache.get(1).getVersion(), is(2));
    }

    @Test(expected = OptimisticException.class)
    public void whenAddWrongVersionThenException() {
        Base base1 = new Base(1, 1);
        Base base2 = new Base(1, 2);
        Cache cache = new Cache();
        cache.add(base1);
        cache.update(base2);
    }
}