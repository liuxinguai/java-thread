package com.github.liuxg.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();

        int c = counter.get();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "counter : "+counter.incrementAndGet());
        }).start();
        Thread.sleep(1000);

        System.out.println(c);
    }
}
