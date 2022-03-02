package com.github.liuxg.thread.fork;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class AddTask1 extends RecursiveTask<Integer> {

    int n;

    public AddTask1(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{"  + n + "}";
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            System.out.println("join() " + n);
            return 1;
        }
        AddTask1 addTask1 = new AddTask1(n-1);
        addTask1.fork();
        System.out.println("fork() " + n +" + "+addTask1);
        int result = addTask1.join() + n;
        System.out.println("join() "+n+" + "+addTask1+" + "+result);
        return result;
    }

    public static void main(String[] args) {
        ForkJoinPool joinPool = new ForkJoinPool();

        System.out.println(joinPool.invoke(new AddTask1(6)));
    }
}
