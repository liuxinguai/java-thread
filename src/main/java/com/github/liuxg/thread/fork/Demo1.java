package com.github.liuxg.thread.fork;

import lombok.AllArgsConstructor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author xinguai.liu
 */
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Fibonacci fibonacci = new Fibonacci(7);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(fibonacci);
        System.out.println(submit.get());
        System.out.println(fibonacci.compute());
    }

    @AllArgsConstructor
    static class Fibonacci extends RecursiveTask<Integer> {

        final int n;

        @Override
        protected Integer compute() {
            if (n <= 1)
                return n;
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            f2.fork();
            return f1.join() + f2.join();
        }
    }
}
