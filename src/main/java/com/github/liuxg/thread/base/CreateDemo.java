package com.github.liuxg.thread.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xinguai.liu
 */
public class CreateDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("run");
            }
        };
        thread.start();
        new Thread(()->{
            System.out.println("Runnable run");
        }).start();
        Callable<String> callable = () -> "liuxg";
        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        System.out.println("future task : "+futureTask.get());

    }
}
