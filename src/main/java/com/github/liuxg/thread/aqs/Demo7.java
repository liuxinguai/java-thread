package com.github.liuxg.thread.aqs;

import java.util.concurrent.CountDownLatch;

public class Demo7 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(()->{
            latch.countDown();
        }).start();
        Thread.sleep(100);
         latch.await();
    }

}
