package com.github.liuxg.thread.futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(()-> "liuxg");
        futureTask.run();
        String s = futureTask.get();
        System.out.println(s);
    }
}
