package com.github.liuxg.thread.threadpools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Collection<Callable<String>> callables = new ArrayList<>(2);
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "1";
            }
        });
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "2";
            }
        });
        String any = service.invokeAny(callables);
        System.out.println(any);
    }

}
