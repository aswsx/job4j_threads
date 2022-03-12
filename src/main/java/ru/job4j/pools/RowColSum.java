package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Alex Gutorov
 * @version 1.0
 * @created 12/03/2022 - 17:23
 */
public class RowColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        public static Sums[] sum(int[][] matrix) {
            Sums[] result = new Sums[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                Sums sums = new Sums();
                sums.setColSum(colSum(matrix, i));
                sums.setRowSum(rowSum(matrix, i));
                result[i] = sums;
            }
            return result;
        }

        private static int colSum(int[][] matrix, int index) {
            int result = 0;
            for (int i = 0; i < matrix.length; i++) {
                result += matrix[index][i];
            }
            return result;
        }

        private static int rowSum(int[][] matrix, int index) {
            int result = 0;
            for (int[] ints : matrix) {
                result += ints[index];
            }
            return result;
        }

        public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
            Sums[] result = new Sums[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                result[i] = sum(matrix, i).get();
            }
            return result;
        }

        public static CompletableFuture<Sums> sum(int[][] matrix, int index) {
            return CompletableFuture.supplyAsync(() -> {
                Sums sums = new Sums();
                sums.setColSum(colSum(matrix, index));
                sums.setRowSum(rowSum(matrix, index));
                return sums;
            });
        }
    }
}
