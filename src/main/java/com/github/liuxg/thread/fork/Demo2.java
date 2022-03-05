package com.github.liuxg.thread.fork;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Demo2 {


    private final static Random random = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long[] array = new long[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random();
        }
        SortTask sortTask = new SortTask(array);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Void> joinTask = forkJoinPool.submit(sortTask);
        joinTask.get();
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    static long random() {
        return random.nextLong() % 1000L;
    }

    static class SortTask extends RecursiveAction {

        final long[] array; final int lo, hi;

        SortTask(long[] array, int lo, int hi) {
            this.array = array; this.lo = lo; this.hi = hi;
        }

        SortTask(long[] array) { this(array, 0, array.length); }

        static final int THRESHOLD = 100;

        @Override
        protected void compute() {
            if (hi - lo <= 100) {
                sortSequentially(array,lo,hi);
            } else {
                int mid = (lo+hi) >>> 1;
                invokeAll(new SortTask(array,lo,mid),new SortTask(array,mid,hi));
                merge(lo,mid,hi);
            }
        }

        private void sortSequentially(long[] array, int lo, int hi) {
            Arrays.sort(array,lo,hi);
        }

        void merge(int lo, int mid, int hi) {
            long[] buf = Arrays.copyOfRange(array, lo, mid);
            for (int i = 0, j = lo, k = mid; i < buf.length; j++) {
                array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
            }
        }

    }
}
