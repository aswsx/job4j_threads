package ru.job4j.pools;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static ru.job4j.pools.RowColSum.*;
import static ru.job4j.pools.RowColSum.Sums.asyncSum;
import static ru.job4j.pools.RowColSum.Sums.sum;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 12/03/2022 - 21:06
 */
public class RowColSumTest {
    public int[][] matrix;

    @Before
    public void setUp() {
        matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    }

    @Test
    public void testSum() {
        Sums[] sums = sum(matrix);
        assertThat(sums[0].getColSum(), is(6));
        assertThat(sums[0].getRowSum(), is(12));
        assertThat(sums[1].getColSum(), is(15));
        assertThat(sums[1].getRowSum(), is(15));
        assertThat(sums[2].getColSum(), is(24));
        assertThat(sums[2].getRowSum(), is(18));
    }

    @Test
    public void testAsyncSum() {
        try {
            Sums[] sums = asyncSum(matrix);
            assertThat(sums[0].getColSum(), is(6));
            assertThat(sums[0].getRowSum(), is(12));
            assertThat(sums[1].getColSum(), is(15));
            assertThat(sums[1].getRowSum(), is(15));
            assertThat(sums[2].getColSum(), is(24));
            assertThat(sums[2].getRowSum(), is(18));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}